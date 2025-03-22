package com.example.schedulehubproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterRequestDto {
    private String name;
    private Integer time;

    FilterRequestDto(String name){
        this.name = name;
    }

    FilterRequestDto(Integer time){
        this.time = time;
    }

    FilterRequestDto(){}

}
