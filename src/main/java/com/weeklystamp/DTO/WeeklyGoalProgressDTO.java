package com.weeklystamp.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeeklyGoalProgressDTO {
    private Long goalId;
    private String placeName;
    private int attendedCount;
    private int targetCount;
    private int progress;
}
