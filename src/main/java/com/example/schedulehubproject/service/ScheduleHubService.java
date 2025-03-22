package com.example.schedulehubproject.service;

import com.example.schedulehubproject.dto.FilterRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;

import java.util.List;

public interface ScheduleHubService {
    ScheduleHubResponseDto saveSchedule(ScheduleHubRequestDto scheduleHubRequestDto);

    ScheduleHubResponseDto findScheduleById(Long scheduleId);

    List<ScheduleHubResponseDto> findAllSchedule();

    List<ScheduleHubResponseDto> findFilteredSchedule(FilterRequestDto filterRequestDto);
}
