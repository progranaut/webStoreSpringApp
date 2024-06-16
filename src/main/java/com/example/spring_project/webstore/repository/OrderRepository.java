package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("FROM Order o WHERE o.user.id = :id")
    List<Order> findAllByUserId(UUID id);

}
