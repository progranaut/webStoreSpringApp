package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.OrderDto;
import com.example.spring_project.webstore.dto.OrderProductRelationDto;
import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProductRelationService orderProductRelationService;

    private final ProductService productService;

    public Order addOrder(User user) {

        Order order = Order.builder()
                .orderNumber(1l)
                .localDateTime(LocalDateTime.now())
                .user(user)
                .build();

        return orderRepository.save(order);

    }

    public ResponseEntity<?> getOrderByUserId(UUID id) {

        List<Order> orders = orderRepository.findAllByUserId(id);

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<OrderDto> orderDtoList = orders.stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .date(order.getLocalDateTime().toString())
                        .build())
                .toList();

        orderDtoList.stream()
                .forEach(orderDto -> orderDto
                        .setRelations(orderProductRelationService
                                .findAllByOrderId(orderDto.getId())
                                .stream().map(relation -> OrderProductRelationDto.builder()
                                        .productDto(productService.toDto(relation.getProduct()))
                                        .relation(relation.getRelationQuantity())
                                        .build())
                                .collect(Collectors.toSet())));

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);

    }
}
