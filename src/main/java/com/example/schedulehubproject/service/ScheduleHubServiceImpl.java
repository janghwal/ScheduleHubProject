package com.example.schedulehubproject.service;

import com.example.schedulehubproject.dto.FilterRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.FilterNameUpdateAt;
import com.example.schedulehubproject.entity.Schedule;
import com.example.schedulehubproject.repository.ScheduleHubRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

import static com.example.schedulehubproject.entity.TimeFilter.*;

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

    @Override
    public ScheduleHubResponseDto findScheduleById(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleHubRepository.findScheduleById(scheduleId);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ScheduleHubResponseDto(optionalSchedule.get());
    }

    @Override
    public List<ScheduleHubResponseDto> findAllSchedule() {
        return scheduleHubRepository.findAllSchedule();
    }

    @Override
    public List<ScheduleHubResponseDto> findFilteredSchedule(FilterRequestDto filterRequestDto) {
        if (filterRequestDto.getTime() == null) {
            return scheduleHubRepository.findFilteredSchedule(new FilterNameUpdateAt(filterRequestDto.getName(), null));
        }
        FilterNameUpdateAt filterNameUpdateAt = switch (filterRequestDto.getTime()) {
            case 1 -> new FilterNameUpdateAt(filterRequestDto.getName(), WITHIN_1_HOUR.timeFiltering());
            case 2 -> new FilterNameUpdateAt(filterRequestDto.getName(), WITHIN_3_HOURS.timeFiltering());
            case 3 -> new FilterNameUpdateAt(filterRequestDto.getName(), WITHIN_1_DAY.timeFiltering());
            case 4 -> new FilterNameUpdateAt(filterRequestDto.getName(), WITHIN_1_WEEK.timeFiltering());
            case 5 -> new FilterNameUpdateAt(filterRequestDto.getName(), WITHIN_1_MONTH.timeFiltering());
            default -> new FilterNameUpdateAt(filterRequestDto.getName(), null);
        };
        return scheduleHubRepository.findFilteredSchedule(filterNameUpdateAt);
    }
}
