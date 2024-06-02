package com.example.spring_project.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityUserDto {

    private UUID id;

    private String email;

    private String password;

    private Set<RoleDto> roles;

}
