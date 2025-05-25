package com.weeklystamp.Service;

import com.weeklystamp.DTO.PlaceRequestDTO;
import com.weeklystamp.DTO.PlaceResponseDTO;

import java.util.List;

public interface PlaceService {

    PlaceResponseDTO createPlace(Long userId, PlaceRequestDTO requestDTO);

    List<PlaceResponseDTO> getPlacesByUserId(Long userId);

    void deletePlace(Long placeId, Long userId);
}

