package com.weeklystamp.Attendance.Entity;

import com.weeklystamp.Place.Entity.Place;
import com.weeklystamp.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// 하루에 출석을 했는지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    private LocalDateTime checkInAt = LocalDateTime.now();

    private boolean attended; // 자동 출석 여부

    // 만약 30분에 1번씩 출석 인정된다면?
    // 헬스장 옆에만 지나갈때, 체크하는것과 겹치면 인정될수도 있음
    // 캐시나 redis로 2회 연속 출석일때만 attended true 되도록

}

