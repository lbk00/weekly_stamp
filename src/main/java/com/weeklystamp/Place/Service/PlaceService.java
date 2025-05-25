package com.weeklystamp.Place.Service;

import com.weeklystamp.Place.DTO.PlaceRequestDTO;
import com.weeklystamp.Place.DTO.PlaceResponseDTO;
import com.weeklystamp.Place.Entity.Place;

import java.util.List;

public interface PlaceService {

    PlaceResponseDTO createPlace(Long userId, PlaceRequestDTO requestDTO);

    List<PlaceResponseDTO> getPlacesByUserId(Long userId);

    void deletePlace(Long placeId, Long userId);
}

