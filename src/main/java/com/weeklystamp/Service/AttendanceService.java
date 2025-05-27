package com.weeklystamp.Service;

import com.weeklystamp.DTO.AttendanceResponseDTO;

import java.util.List;

public interface AttendanceService {

    AttendanceResponseDTO checkIn(Long userId, Long placeId);

    List<AttendanceResponseDTO> getTodayAttendances(Long userId);
}

