package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, UUID> {
}
