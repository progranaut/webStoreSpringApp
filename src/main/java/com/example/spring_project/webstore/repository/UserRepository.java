package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("FROM User u WHERE u.securityUser.id = :id")
    Optional<User> findBySecUserId(UUID id);

}
