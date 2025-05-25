package com.weeklystamp.Service;

import com.weeklystamp.DTO.UserResponseDTO;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO registerUser(String deviceId) {
        // 이미 등록된 사용자 존재 시 반환
        User user = userRepository.findByDeviceId(deviceId)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .deviceId(deviceId)
                            .isActive(true)
                            .activeAt(LocalDateTime.now())
                            .build();
                    return userRepository.save(newUser);
                });

        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        userRepository.delete(user); // 삭제
    }

    /*스케줄러*/

    // 매일 새벽 3시 30일 이상 활동없으면 비활성화
    @Scheduled(cron = "0 0 3 * * *")
    public void markInactiveUsers() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(30);

        List<User> inactiveUsers = userRepository.findByActiveAtBeforeAndIsActiveTrue(threshold);
        for (User user : inactiveUsers) {
            user.deactivate(); // isActive = false
        }
        userRepository.saveAll(inactiveUsers);
    }

    // 비활성 사용자 일괄 삭제
    @Scheduled(cron = "0 0 4 * * *")
    public void deleteInactiveUsers() {
        List<User> toDelete = userRepository.findByIsActiveFalse();
        userRepository.deleteAll(toDelete);
    }

}
