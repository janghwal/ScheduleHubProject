package com.example.schedulehubproject.dto;

import com.example.schedulehubproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleHubResponseDto {

    private Long scheduleId;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ScheduleHubResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createAt = schedule.getCreateAt();
        this.updateAt = schedule.getUpdateAt();
    }

    public ScheduleHubResponseDto(long scheduleId, String name, String title, String contents) {
        this.scheduleId = scheduleId;
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
}
