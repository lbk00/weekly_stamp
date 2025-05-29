package com.weeklystamp.DTO;

import com.weeklystamp.Entity.Attendance;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AttendanceHistoryDTO {
    private LocalDate date;
    private String placeName;
    private boolean attended;

    public static AttendanceHistoryDTO fromEntity(Attendance attendance) {
        return AttendanceHistoryDTO.builder()
                .date(attendance.getCheckInAt().toLocalDate())
                .placeName(attendance.getPlace().getName())
                .attended(attendance.isAttended())
                .build();
    }
}
