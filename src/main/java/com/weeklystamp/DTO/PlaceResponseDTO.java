package com.weeklystamp.DTO;

import com.weeklystamp.Entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PlaceResponseDTO {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double radius;
    private int goalPerWeek;
    private LocalDateTime createdAt;

    public static PlaceResponseDTO fromEntity(Place place) {
        return PlaceResponseDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .radius(place.getRadius())
                .goalPerWeek(place.getGoalPerWeek())
                .createdAt(place.getCreatedAt())
                .build();
    }
}
