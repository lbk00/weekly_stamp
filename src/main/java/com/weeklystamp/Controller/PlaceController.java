package com.weeklystamp.Controller;

import com.weeklystamp.Common.ApiResponse;
import com.weeklystamp.DTO.PlaceRequestDTO;
import com.weeklystamp.DTO.PlaceResponseDTO;
import com.weeklystamp.Service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@Validated
public class PlaceController {

    private final PlaceService placeService;

    // 장소 등록
    @PostMapping
    public ResponseEntity<ApiResponse<PlaceResponseDTO>> createPlace(
            @RequestParam Long userId,
            @Valid @RequestBody PlaceRequestDTO requestDTO) {

        PlaceResponseDTO response = placeService.createPlace(userId, requestDTO);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 사용자별 장소 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<PlaceResponseDTO>>> getPlaces(
            @RequestParam Long userId) {

        List<PlaceResponseDTO> response = placeService.getPlacesByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


    // 장소 삭제
    @DeleteMapping("/{placeId}")
    public ResponseEntity<ApiResponse<Void>> deletePlace(
            @PathVariable Long placeId,
            @RequestParam Long userId) {

        placeService.deletePlace(placeId, userId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}

