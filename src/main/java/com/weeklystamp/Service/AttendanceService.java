package com.weeklystamp.Service;

import com.weeklystamp.DTO.AttendanceResponseDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {

    AttendanceResponseDTO checkIn(Long userId, Long placeId);
    List<LocalDate> getMonthlyAttendance(Long userId, YearMonth yearMonth);
    List<AttendanceResponseDTO> getTodayAttendances(Long userId);
}

