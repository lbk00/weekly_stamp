package com.weeklystamp.Place.Repository;

import com.weeklystamp.Place.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String name); // 사용자별 장소 중복 방지용
}

