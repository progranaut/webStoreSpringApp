package com.example.spring_project.webstore.dto;

import com.example.spring_project.security.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNameAndRoleDto {

    private String name;

    private Set<RoleDto> roles;

}
