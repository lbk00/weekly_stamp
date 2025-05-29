package com.weeklystamp.Service;

import com.weeklystamp.DTO.AttendanceHistoryDTO;
import com.weeklystamp.DTO.AttendanceResponseDTO;
import com.weeklystamp.Entity.Attendance;
import com.weeklystamp.Repository.AttendanceRepository;
import com.weeklystamp.Entity.Place;
import com.weeklystamp.Repository.PlaceRepository;
import com.weeklystamp.Entity.User;
import com.weeklystamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final CacheManager cacheManager;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;


    private String cacheKey(Long userId, Long placeId) {
        return userId + "-" + placeId;
    }

    @Override
    public AttendanceResponseDTO checkIn(Long userId, Long placeId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

        String key = cacheKey(userId, placeId); // 캐시에 사용할 키
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastCheckIn = null;

        // 최근 출석 시간 조회
        Cache cache = cacheManager.getCache("checkInCache");
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null) {
                lastCheckIn = (LocalDateTime) wrapper.get();
            }
        }

        boolean attended = lastCheckIn != null && lastCheckIn.isAfter(now.minusMinutes(30));

        Attendance attendance = Attendance.builder()
                .user(user)
                .place(place)
                .checkInAt(now)
                .attended(attended)
                .build();

        attendanceRepository.save(attendance);

        if (cache != null) {
            cache.put(key, now);
        }

        return AttendanceResponseDTO.fromEntity(attendance);
    }


    // 월별 출석 현황
    @Override
    public List<LocalDate> getMonthlyAttendance(Long userId, YearMonth yearMonth) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        List<Attendance> attendances = attendanceRepository.findByUserAndAttendedIsTrueAndCheckInAtBetween(
                user, start.atStartOfDay(), end.atTime(LocalTime.MAX));

        return attendances.stream()
                .map(a -> a.getCheckInAt().toLocalDate())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // 지난달 히스토리 조회
    @Override
    public List<AttendanceHistoryDTO> getMonthlyHistory(Long userId, YearMonth yearMonth) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        List<Attendance> records = attendanceRepository.findByUserAndCheckInAtBetween(
                user, start.atStartOfDay(), end.atTime(LocalTime.MAX)
        );

        return records.stream()
                .map(AttendanceHistoryDTO::fromEntity)
                .sorted(Comparator.comparing(AttendanceHistoryDTO::getDate))
                .collect(Collectors.toList());
    }


    // 오늘 날짜 기준 사용자 출석 기록 전체 조회
    @Override
    public List<AttendanceResponseDTO> getTodayAttendances(Long userId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        List<Attendance> records = attendanceRepository.findByUserIdAndCheckInAtBetween(userId, start, end);
        return records.stream()
                .map(AttendanceResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

