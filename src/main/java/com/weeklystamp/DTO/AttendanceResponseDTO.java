package com.weeklystamp.DTO;

import com.weeklystamp.Entity.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AttendanceResponseDTO {

    private Long id;
    private Long userId;
    private Long placeId;
    private Long weeklyGoalId;
    private LocalDateTime checkInAt;
    private boolean attended;

    public static AttendanceResponseDTO fromEntity(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .id(attendance.getId())
                .userId(attendance.getUser().getId())
                .placeId(attendance.getPlace().getId())
                .weeklyGoalId(attendance.getWeeklyGoal() != null ? attendance.getWeeklyGoal().getId() : null)
                .checkInAt(attendance.getCheckInAt())
                .attended(attendance.isAttended())
                .build();
    }
}
