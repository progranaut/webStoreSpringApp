package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.OrderProductRelation;
import com.example.spring_project.webstore.repository.OrderProductRelationRepository;
import com.example.spring_project.webstore.repository.UserProductRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProductRelationService {

    private final OrderProductRelationRepository orderProductRelationRepository;

    public void addRelations(List<OrderProductRelation> orderProductRelations) {

        orderProductRelationRepository.saveAll(orderProductRelations);

    }

    public List<OrderProductRelation> findAllByOrderId(UUID id) {

        return orderProductRelationRepository.findAllByOrderId(id);

    }
}
