package com.example.schedulehubproject.service;

import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;

public interface ScheduleHubService {
    ScheduleHubResponseDto saveSchedule(ScheduleHubRequestDto scheduleHubRequestDto);
}
