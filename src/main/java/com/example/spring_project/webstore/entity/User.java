package com.example.spring_project.webstore.entity;

import com.example.spring_project.security.entity.SecurityUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "security_user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private SecurityUser securityUser;

    @OneToMany(mappedBy = "user")
    private Set<UserProductRelation> userProductRelationSet;

    public void addProductQuantity(UserProductRelation userProductRelation) {
        this.userProductRelationSet.add(userProductRelation);
        userProductRelation.setUser(this);
    }

    public void removeProductQuantity(UserProductRelation userProductRelation) {
        this.userProductRelationSet.remove(userProductRelation);
        userProductRelation.setUser(null);
    }

//    @JoinTable(name = "user_product_relation",
//              joinColumns = @JoinColumn(name = "user_id"),
//              inverseJoinColumns = @JoinColumn(name = "product_id"))
//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Product> basket;

}
