package com.example.spring_project.webstore.entity;

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

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private int availability;

    @OneToMany(mappedBy = "product")
    Set<UserProductRelation> userProductRelationSet;

//    public void addProductQuantity(UserProductRelation userProductRelation) {
//        this.userProductRelationSet.add(userProductRelation);
//        userProductRelation.setProduct(this);
//    }
//
//    public void removeProductQuantity(UserProductRelation userProductRelation) {
//        this.userProductRelationSet.remove(userProductRelation);
//        userProductRelation.setProduct(null);
//    }

}
