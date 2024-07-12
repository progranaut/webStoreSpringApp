package com.example.spring_project.alterbot.controller;

import com.example.spring_project.alterbot.dto.CallOrderDto;
import com.example.spring_project.alterbot.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/call-order")
    public void callOrder(@RequestBody CallOrderDto callOrderDto) {

        messageService.callOrder(callOrderDto);

    }

}
