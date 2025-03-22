package com.example.schedulehubproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FilterNameUpdateAt {
    private String name;
    private LocalDateTime filteredTime;

    FilterNameUpdateAt(String name){
        this.name = name;
    }

    FilterNameUpdateAt(LocalDateTime filteredTime){
        this.filteredTime = filteredTime;
    }
}
