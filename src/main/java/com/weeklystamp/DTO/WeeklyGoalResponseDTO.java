package com.weeklystamp.DTO;

import lombok.Data;

@Data
public class WeeklyGoalResponseDTO {
    private Long id;
    private String placeName;
    private int targetCount;
}
