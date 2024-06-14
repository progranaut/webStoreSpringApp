package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order addOrder(User user) {

        Order order = Order.builder()
                .orderNumber(1l)
                .localDateTime(LocalDateTime.now())
                .user(user)
                .build();

        return orderRepository.save(order);

    }
}
