package com.example.schedulehubproject.controller;

import com.example.schedulehubproject.dto.FilterRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.dto.FilterRequestDto;
import com.example.schedulehubproject.entity.Schedule;
import com.example.schedulehubproject.service.ScheduleHubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ScheduleHub")
public class ScheduleHubController {
    private final ScheduleHubService scheduleHubService;

    public ScheduleHubController(ScheduleHubService scheduleHubService) {
        this.scheduleHubService = scheduleHubService;
    }

    //일정 등록
    @PostMapping
    public ResponseEntity<ScheduleHubResponseDto> createSchedule(@RequestBody ScheduleHubRequestDto scheduleHubRequestDto){
        return new ResponseEntity<>(scheduleHubService.saveSchedule(scheduleHubRequestDto), HttpStatus.CREATED);
    }

    //일정 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleHubResponseDto> findScheduleById(@PathVariable Long scheduleId){
        return new ResponseEntity<>(scheduleHubService.findScheduleById(scheduleId), HttpStatus.OK);
    }

    //일정 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleHubResponseDto> findAllSchedule(){
        return scheduleHubService.findAllSchedule();
    }

    //조건에 맞는 일정 조회
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleHubResponseDto> findFilteredSchedule(@RequestBody FilterRequestDto filterRequestDto){
        return scheduleHubService.findFilteredSchedule(filterRequestDto);
    }


    //일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleHubResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleHubRequestDto scheduleHubRequestDto
    ){
        return new ResponseEntity<>(scheduleHubService.updateSchedule(scheduleId ,scheduleHubRequestDto), HttpStatus.OK);
    }


    //일정 삭제
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleHubRequestDto scheduleHubRequestDto
    ){
        scheduleHubService.deleteSchedule(scheduleId, scheduleHubRequestDto);
    }

}
