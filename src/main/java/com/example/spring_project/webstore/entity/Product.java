package com.example.spring_project.webstore.entity;

import com.example.spring_project.webstore.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private int availability;

    @Column(name = "visibility")
    private boolean visibility;

    @OneToMany(mappedBy = "product")
    Set<UserProductRelation> userProductRelationSet;

}
