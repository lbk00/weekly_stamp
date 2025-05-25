package com.weeklystamp.Repository;

import com.weeklystamp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDeviceId(String deviceId);

    List<User> findByActiveAtBeforeAndIsActiveTrue(LocalDateTime threshold);

    List<User> findByIsActiveFalse();


}
