package com.weeklystamp.Service;


import com.weeklystamp.DTO.WeeklyGoalRequestDTO;
import com.weeklystamp.DTO.WeeklyGoalResponseDTO;

import java.util.List;

public interface WeeklyGoalService {

    // 주간 목표 생성
    WeeklyGoalResponseDTO createGoal(WeeklyGoalRequestDTO dto);

    // 특정 사용자 목표 전체 조회
    List<WeeklyGoalResponseDTO> getGoalsByUser(Long userId);

    // 목표 삭제
    void deleteGoal(Long goalId);

}
