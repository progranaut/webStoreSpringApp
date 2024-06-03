package com.example.spring_project.webstore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test")
    public String test() {
        return "Привет тестовый текст";
    }

    @GetMapping("/test2")
    public String test2() {
        return "еще тестовый текст";
    }

    @GetMapping("/info")
    public String info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
