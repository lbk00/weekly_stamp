package com.weeklystamp.User.Service;

import com.weeklystamp.User.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {

    /**
     * deviceId로 사용자 등록 (기존 사용자 있으면 생성하지 않음)
     * @param deviceId 디바이스 고유 ID
     * @return 등록된 사용자 정보
     */
    UserResponseDTO registerUser(String deviceId);

    /**
     * 사용자 삭제
     * @param userId 사용자 ID
     */
    void deleteUser(Long userId);

    /**
     * 사용자 비활성화 (isActive = false)
     */
    void deactivateUser(Long userId);


}
