package com.example.spring_project.alterbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallOrderDto {

    private String name;

    private String phone;

    private String message;

}
