package com.example.spring_project.webstore.repository;

import com.example.spring_project.webstore.entity.UserProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProductRelationRepository extends JpaRepository<UserProductRelation, UUID> {

    @Query("FROM UserProductRelation upr WHERE upr.user.id = :userId AND upr.product.id = :productId")
    Optional<UserProductRelation> findRelationByIds(UUID userId, UUID productId);

    List<UserProductRelation> findAllByUserId(UUID userId);

    //@Query("DELETE FROM UserProductRelation u WHERE u.user.id = :userId")
    //void deleteAllByUserId(UUID userId);

}
