package com.weeklystamp.DTO;

import lombok.Data;

@Data
public class WeeklyGoalRequestDTO {
    private Long userId;
    private Long placeId;
    private int targetCount; // 주간 목표 횟수
}
