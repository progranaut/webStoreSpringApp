package com.example.spring_project.webstore.dto;

import com.example.spring_project.security.dto.SecurityUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;

    private String name;

    private String phoneNumber;

    private String address;

    private SecurityUserDto securityUserDto;

}
