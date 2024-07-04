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

    private final UserService userService;

    public Order addOrder(User user) {

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .localDateTime(LocalDateTime.now())
                .user(user)
                .build();
        orderRepository.saveOrder(order.getId(), order.getLocalDateTime(), order.getUser().getId());

        return orderRepository.findById(order.getId()).orElseThrow();
    }

    public ResponseEntity<?> getOrderByUserId(UUID id) {

        List<Order> orders = orderRepository.findAllByUserId(id);

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toOrderDtoList(orders), HttpStatus.OK);

    }

    public List<OrderDto> getAllOrders() {

        return orderRepository.findAll().stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .userDto(userService.toDto(order.getUser()))
                        .build())
                .collect(Collectors.toList());

    }

    public ResponseEntity<?> getAllCurrentUserOrders() {

        User user = userService.getCurrentUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Order> orders = orderRepository.findAllByUserId(user.getId());

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toOrderDtoList(orders), HttpStatus.OK);
    }

    private List<OrderDto> toOrderDtoList(List<Order> orders) {
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
                                        .relationPrice(relation.getRelationPrice())
                                        .build())
                                .collect(Collectors.toSet())));
        return orderDtoList;
    }
}
