package com.weeklystamp.Scheduler;


import com.weeklystamp.Entity.Place;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Entity.WeeklyGoal;
import com.weeklystamp.Repository.PlaceRepository;
import com.weeklystamp.Repository.UserRepository;
import com.weeklystamp.Service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceScheduler {

    private final PlaceRepository placeRepository;
    private final AttendanceService attendanceService;
    private final UserRepository userRepository;

    // 출석 스케줄러
    // 최악의 시나리오인 경우에도 30분 머물면 출석되도록
    @Scheduled(cron = "0 */15 * * * *")
    public void checkInAllUsers() {
        List<User> users = userRepository.findByIsActiveTrue();

        for (User user : users) {
            List<Place> places = placeRepository.findByUserId(user.getId());
            for (Place place : places) {
                attendanceService.checkIn(user.getId(), place.getId());
            }
            user.updateActivity();
        }
        userRepository.saveAll(users);
    }


}
