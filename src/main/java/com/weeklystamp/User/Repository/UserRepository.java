package com.weeklystamp.User.Repository;

import com.weeklystamp.User.DTO.UserResponseDTO;
import com.weeklystamp.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDeviceId(String deviceId);

    List<User> findByActiveAtBeforeAndIsActiveTrue(LocalDateTime threshold);

    List<User> findByIsActiveFalse();


}
