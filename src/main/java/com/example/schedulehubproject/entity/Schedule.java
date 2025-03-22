package com.example.schedulehubproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long scheduleId;
    private String name;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Schedule(String name, String password, String title, String contents) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }


    public Schedule(long scheduleId, String name, String title, String contents, Timestamp createAt, Timestamp updateAt) {
        this.scheduleId = scheduleId;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt.toLocalDateTime();
        this.updateAt = updateAt.toLocalDateTime();
    }
}
