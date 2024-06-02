package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

}
