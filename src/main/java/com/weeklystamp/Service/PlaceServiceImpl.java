package com.weeklystamp.Service;

import com.weeklystamp.DTO.PlaceRequestDTO;
import com.weeklystamp.DTO.PlaceResponseDTO;
import com.weeklystamp.Entity.Place;
import com.weeklystamp.Repository.PlaceRepository;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @Override
    public PlaceResponseDTO createPlace(Long userId, PlaceRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Place place = Place.builder()
                .user(user)
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .radius(dto.getRadius())
                .goalPerWeek(dto.getGoalPerWeek())
                .createdAt(LocalDateTime.now())
                .build();

        Place saved = placeRepository.save(place);
        return PlaceResponseDTO.fromEntity(saved);
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByUserId(Long userId) {
        List<Place> places = placeRepository.findByUserId(userId);
        return places.stream()
                .map(PlaceResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlace(Long placeId, Long userId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

        if (!place.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("해당 장소를 삭제할 권한이 없습니다.");
        }

        placeRepository.delete(place);
    }
}

