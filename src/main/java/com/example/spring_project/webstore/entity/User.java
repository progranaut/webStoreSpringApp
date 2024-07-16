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
}
