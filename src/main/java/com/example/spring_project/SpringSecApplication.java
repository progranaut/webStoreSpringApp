package com.example.spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringSecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}

}
