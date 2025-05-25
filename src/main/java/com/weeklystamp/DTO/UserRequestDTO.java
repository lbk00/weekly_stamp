package com.weeklystamp.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// deviceId로 사용자 등록시 사용
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDTO {

    @NotBlank(message = "deviceId는 필수입니다.")
    private String deviceId;

}
