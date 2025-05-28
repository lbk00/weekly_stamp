package com.weeklystamp.DTO;

import com.weeklystamp.Entity.WeeklyGoal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyGoalResponseDTO {
    private Long id;
    private String placeName;
    private int targetCount;

    public static WeeklyGoalResponseDTO fromEntity(WeeklyGoal goal) {
        return WeeklyGoalResponseDTO.builder()
                .id(goal.getId())
                .placeName(goal.getPlace().getName())
                .targetCount(goal.getTargetCount())
                .build();
    }

}
