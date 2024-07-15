package com.example.spring_project.messagebot.feign;

import com.example.spring_project.messagebot.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "client", url = "${feign.url}")
public interface FeignImpl {

    @PostMapping("/send")
    void sendMessage(@RequestBody MessageDto messageDto);

}
