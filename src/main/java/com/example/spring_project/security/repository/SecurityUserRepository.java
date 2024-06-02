package com.example.spring_project.security.repository;

import com.example.spring_project.security.entity.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, UUID> {

    @Query("FROM SecurityUser u JOIN FETCH u.roles WHERE u.email = :username")
    Optional<SecurityUser> findByUsername(String username);

}
