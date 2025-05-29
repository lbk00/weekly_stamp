package com.weeklystamp.Controller;

import com.weeklystamp.DTO.AttendanceHistoryDTO;
import com.weeklystamp.DTO.AttendanceResponseDTO;
import com.weeklystamp.Service.AttendanceService;
import com.weeklystamp.Common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
@Validated
public class AttendanceController {

    private final AttendanceService attendanceService;

    // 출석 체크 (30분 중복 방지 + attended 판단 포함)
    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<AttendanceResponseDTO>> checkIn(
            @RequestParam Long userId,
            @RequestParam Long placeId) {

        AttendanceResponseDTO response = attendanceService.checkIn(userId, placeId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 오늘 날짜의 출석 기록 전체 조회
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<AttendanceResponseDTO>>> getTodayAttendances(
            @RequestParam Long userId) {

        List<AttendanceResponseDTO> response = attendanceService.getTodayAttendances(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 현재 월별 출석 현황 조회
    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<List<LocalDate>>> getMonthlyAttendance(
            @RequestParam Long userId,
            @RequestParam String yearMonth // 형식: "2025-05"
    ) {
        YearMonth ym = YearMonth.parse(yearMonth);
        List<LocalDate> data = attendanceService.getMonthlyAttendance(userId, ym);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    // 지난달 출석 현황 조회
    @GetMapping("/monthly/history")
    public ResponseEntity<ApiResponse<List<AttendanceHistoryDTO>>> getMonthlyHistory(
            @RequestParam Long userId,
            @RequestParam String yearMonth
    ) {
        YearMonth ym = YearMonth.parse(yearMonth);
        List<AttendanceHistoryDTO> data = attendanceService.getMonthlyHistory(userId, ym);
        return ResponseEntity.ok(ApiResponse.success(data));
    }


}

