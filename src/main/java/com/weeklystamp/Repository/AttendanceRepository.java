package com.weeklystamp.Repository;

import com.weeklystamp.Entity.Attendance;
import com.weeklystamp.Entity.Place;
import com.weeklystamp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUserIdAndPlaceIdAndCheckInAtBetween(
            Long userId, Long placeId, LocalDateTime start, LocalDateTime end
    );

    boolean existsByUserIdAndPlaceIdAndCheckInAtBetween(
            Long userId, Long placeId, LocalDateTime start, LocalDateTime end
    );

    List<Attendance> findByUserIdAndCheckInAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    int countByUserAndPlaceAndAttendedIsTrueAndCheckInAtBetween(User user, Place place, LocalDateTime start, LocalDateTime end);

    List<Attendance> findByUserAndAttendedIsTrueAndCheckInAtBetween(User user, LocalDateTime start, LocalDateTime end);


}
