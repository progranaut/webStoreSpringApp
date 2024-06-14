package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.OrderProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderProductRelationRepository extends JpaRepository<OrderProductRelation, UUID> {
}
