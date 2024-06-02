package com.example.spring_project.security.controller;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    public final RoleService roleService;

    @PostMapping("/add")
    public void addRole(@RequestBody RoleDto roleDto) {
        roleService.addRole(roleDto);
    }

}
