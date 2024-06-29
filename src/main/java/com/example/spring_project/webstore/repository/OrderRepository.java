package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.Order;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("FROM Order o WHERE o.user.id = :id")
    List<Order> findAllByUserId(UUID id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orders (id, date, user_id)" +
            "VALUES (:id, :time, :userId)", nativeQuery = true)
    void saveOrder(UUID id, LocalDateTime time, UUID userId);

}
