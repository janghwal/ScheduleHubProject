package com.example.schedulehubproject.repository;

import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHubRepository {
    ScheduleHubResponseDto saveSchedule(Schedule schedule);
}
