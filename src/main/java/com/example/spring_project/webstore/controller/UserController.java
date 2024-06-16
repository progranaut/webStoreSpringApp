package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PostMapping("/change")
    public void changeUser(@RequestBody UserDto userDto) {
        userService.changeUser(userDto);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();

    }
}
