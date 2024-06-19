package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.OrderDto;
import com.example.spring_project.webstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all-user-order/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable String id) {

        return orderService.getOrderByUserId(UUID.fromString(id));

    }

    @GetMapping("/all-orders")
    public List<OrderDto> getAllOrders() {

        return orderService.getAllOrders();

    }

}
