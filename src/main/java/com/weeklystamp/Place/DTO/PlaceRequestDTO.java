package com.weeklystamp.Place.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceRequestDTO {

    @NotBlank(message = "장소 이름은 필수입니다.")
    private String name;

    @NotNull(message = "위도는 필수입니다.")
    private Double latitude;

    @NotNull(message = "경도는 필수입니다.")
    private Double longitude;

    @NotNull(message = "반경은 필수입니다.")
    private Double radius;

    @NotNull(message = "주간 목표 횟수는 필수입니다.")
    private Integer goalPerWeek;
}
