package com.weeklystamp.DTO;

import com.weeklystamp.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String deviceId;
    private boolean isActive;
    private LocalDateTime activeAt;

    // Entity -> DTO
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getDeviceId(),
                user.isActive(),
                user.getActiveAt()
        );
    }

}
