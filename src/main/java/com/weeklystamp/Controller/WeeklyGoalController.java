package com.weeklystamp.Controller;

import com.weeklystamp.Common.ApiResponse;
import com.weeklystamp.DTO.WeeklyGoalProgressDTO;
import com.weeklystamp.DTO.WeeklyGoalRequestDTO;
import com.weeklystamp.DTO.WeeklyGoalResponseDTO;
import com.weeklystamp.Service.WeeklyGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class WeeklyGoalController {

    private final WeeklyGoalService weeklyGoalService;

    // 이번주 목표 달성률
    @GetMapping("/goals/progress")
    public ResponseEntity<ApiResponse<List<WeeklyGoalProgressDTO>>> getGoalProgress(@RequestParam Long userId) {
        List<WeeklyGoalProgressDTO> data = weeklyGoalService.getGoalProgress(userId);
        return ResponseEntity.ok(ApiResponse.success(data));
    }


    // 주간 목표 등록
    @PostMapping
    public ResponseEntity<ApiResponse<WeeklyGoalResponseDTO>> createGoal(@RequestBody WeeklyGoalRequestDTO requestDTO) {
        WeeklyGoalResponseDTO response = weeklyGoalService.createGoal(requestDTO);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


    // 특정 사용자의 주간 목표 전체 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<WeeklyGoalResponseDTO>>> getUserGoals(@PathVariable Long userId) {
        List<WeeklyGoalResponseDTO> response = weeklyGoalService.getGoalsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 주간 목표 삭제
    @DeleteMapping("/{goalId}")
    public ResponseEntity<ApiResponse<Void>> deleteGoal(@PathVariable Long goalId) {
        weeklyGoalService.deleteGoal(goalId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
