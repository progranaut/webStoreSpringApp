package com.example.spring_project.webstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product_quantity")
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    int quantity;

}
