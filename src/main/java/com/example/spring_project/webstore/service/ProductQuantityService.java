package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.entity.ProductQuantity;
import com.example.spring_project.webstore.repository.ProductQuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductQuantityService {

    private final ProductQuantityRepository productQuantityRepository;

    public void addProductQuantity(ProductQuantity productQuantity) {
        productQuantityRepository.save(productQuantity);
    }

}
