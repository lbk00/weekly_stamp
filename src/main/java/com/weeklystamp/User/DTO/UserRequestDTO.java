package com.weeklystamp.User.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// deviceId로 사용자 등록시 사용
@Getter
public class UserRequestDTO {

    @NotBlank(message = "deviceId는 필수입니다.")
    private String deviceId;

}
