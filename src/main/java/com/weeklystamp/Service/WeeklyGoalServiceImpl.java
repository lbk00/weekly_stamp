package com.weeklystamp.Service;

import com.weeklystamp.DTO.WeeklyGoalRequestDTO;
import com.weeklystamp.DTO.WeeklyGoalResponseDTO;
import com.weeklystamp.Entity.Place;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Entity.WeeklyGoal;
import com.weeklystamp.Repository.PlaceRepository;
import com.weeklystamp.Repository.UserRepository;
import com.weeklystamp.Repository.WeeklyGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WeeklyGoalServiceImpl implements WeeklyGoalService {

    private final WeeklyGoalRepository weeklyGoalRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Override
    public WeeklyGoalResponseDTO createGoal(WeeklyGoalRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Place place = placeRepository.findById(dto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

        // 중복 생성 방지
        if (weeklyGoalRepository.existsByUserAndPlace(user, place)) {
            throw new IllegalStateException("이미 해당 장소에 대한 목표가 존재합니다.");
        }

        WeeklyGoal goal = WeeklyGoal.builder()
                .user(user)
                .place(place)
                .targetCount(dto.getTargetCount())
                .build();

        WeeklyGoal saved = weeklyGoalRepository.save(goal);

        return WeeklyGoalResponseDTO.fromEntity(saved);
    }

    @Override
    public List<WeeklyGoalResponseDTO> getGoalsByUser(Long userId) {
        return weeklyGoalRepository.findByUserId(userId).stream()
                .map(WeeklyGoalResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGoal(Long goalId) {
        if (!weeklyGoalRepository.existsById(goalId)) {
            throw new IllegalArgumentException("목표가 존재하지 않습니다.");
        }
        weeklyGoalRepository.deleteById(goalId);
    }
}
