package com.example.schedulehubproject.controller;

import com.example.schedulehubproject.dto.ScheduleHubRequestDto;
import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.Schedule;
import com.example.schedulehubproject.service.ScheduleHubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @GetMapping("/{scheduleId}")


    //일정 전체 조회
//    @GetMapping


    //조건에 맞는 일정 조회
//    @GetMapping("/filter/{scheduleId}&{timeFilter}")


    //일정 수정
//    @PutMapping("/{scheduleId}")


    //일정 삭제
//    @DeleteMapping("/{scheduleId}")
}
