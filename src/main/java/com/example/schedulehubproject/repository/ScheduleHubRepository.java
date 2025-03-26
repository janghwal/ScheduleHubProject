package com.example.schedulehubproject.repository;

import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.FilterNameUpdateAt;
import com.example.schedulehubproject.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ScheduleHubRepository {
    ScheduleHubResponseDto saveSchedule(Schedule schedule);

    Optional<Schedule> findScheduleById(Long scheduleId);

    List<ScheduleHubResponseDto> findAllSchedule();

    List<ScheduleHubResponseDto> findFilteredSchedule(FilterNameUpdateAt filterNameUpdateAt);

    String getPassword(Long scheduleId);

    int updateSchedule(Schedule schedule);

    int deleteSchedule(Long scheduleId);
}
