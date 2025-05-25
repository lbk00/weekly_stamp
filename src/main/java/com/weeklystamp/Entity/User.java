package com.weeklystamp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String deviceId;

    private boolean isActive = true;

    private LocalDateTime activeAt; // 마지막 활동일

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    // 30일 이상 활동안한 사용자 삭제위해 기록
    public void updateActivity() {
        this.activeAt = LocalDateTime.now();
    }


}