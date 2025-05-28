package com.weeklystamp.Scheduler;

import com.weeklystamp.Entity.User;
import com.weeklystamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserScheduler {

    private final UserRepository userRepository;

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
