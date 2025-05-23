package com.weeklystamp.Place.Entity;

import com.weeklystamp.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private Double latitude;
    private Double longitude;
    private Double radius;

    private int goalPerWeek; // 주간 목표 횟수

    private LocalDateTime createdAt = LocalDateTime.now();


}

