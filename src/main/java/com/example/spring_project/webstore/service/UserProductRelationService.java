package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.entity.UserProductRelation;
import com.example.spring_project.webstore.repository.UserProductRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProductRelationService {

    private final UserProductRelationRepository userProductRelationRepository;

    public void addUserProductRelation(User user, Product product) {

        UserProductRelation userProductRelation;

        try {

            userProductRelation = getRelation(user.getId(), product.getId());
            int quantity = userProductRelation.getQuantity();
            userProductRelation.setQuantity(++quantity);
            //userProductRelationRepository.save(userProductRelation);

        } catch (Exception e) {
            userProductRelation = UserProductRelation.builder()
                    .user(user)
                    .product(product)
                    .quantity(1)
                    .build();
            //userProductRelationRepository.save(userProductRelation);
        }

//        if (userProductRelation == null) {
//            userProductRelation = UserProductRelation.builder()
//                    .user(user)
//                    .product(product)
//                    .quantity(1)
//                    .build();
//        } else {
//            int quantity = userProductRelation.getQuantity();
//            userProductRelation.setQuantity(++quantity);
//        }

        userProductRelationRepository.save(userProductRelation);
    }

    public UserProductRelation getRelation(UUID userId, UUID productId) {

        return userProductRelationRepository.findRelationByIds(userId, productId)
                .orElseThrow();

    }

    public List<UserProductRelation> getRelations(User user) {

        return userProductRelationRepository.findAllByUserId(user.getId());

    }

    public void delUserProductRelation(User user, Product product) {

        UserProductRelation userProductRelation = getRelation(user.getId(), product.getId());

        if (userProductRelation.getQuantity() > 1) {

            int quantity = userProductRelation.getQuantity();
            userProductRelation.setQuantity(--quantity);
            userProductRelationRepository.save(userProductRelation);

        } else {

            userProductRelationRepository.delete(userProductRelation);

        }

    }
}
