package com.weeklystamp.Repository;

import com.weeklystamp.Entity.Place;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Entity.WeeklyGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal, Long> {

    // 특정 사용자 목표 전체 조회
    List<WeeklyGoal> findByUserId(Long userId);

    // 사용자 + 장소 조합으로 목표 조회 (중복 생성 방지용)
    Optional<WeeklyGoal> findByUserAndPlace(User user, Place place);

    boolean existsByUserAndPlace(User user, Place place);
}