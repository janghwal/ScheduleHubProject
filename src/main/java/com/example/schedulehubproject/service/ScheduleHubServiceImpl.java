package com.example.schedulehubproject.service;

import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.Schedule;
import com.example.schedulehubproject.repository.ScheduleHubRepository;

import org.springframework.stereotype.Service;

@Service
public class ScheduleHubServiceImpl implements  ScheduleHubService{
    private final ScheduleHubRepository scheduleHubRepository;

    public ScheduleHubServiceImpl(ScheduleHubRepository scheduleHubRepository) {
        this.scheduleHubRepository = scheduleHubRepository;
    }

    @Override
    public ScheduleHubResponseDto saveSchedule(ScheduleHubRequestDto scheduleHubRequestDto) {

        Schedule schedule = new Schedule(scheduleHubRequestDto.getName(), scheduleHubRequestDto.getPassword(), scheduleHubRequestDto.getTitle(), scheduleHubRequestDto.getContents());

        return scheduleHubRepository.saveSchedule(schedule);
    }
}
