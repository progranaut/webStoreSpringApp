package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;

    private final ProductMapper productMapper;

    private final ProductService productService;

    public void addProductInBasket(UUID id) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return;
        }

        Product product = productService.findProductById(id);

        user.getBasket().add(product);

        userService.changeUser(user);

    }

    public List<ProductDto> getProductInBasket() {

        User user = userService.getCurrentUser();

        if (user == null) {
            return null;
        }

        return user.getBasket().stream()
                .map(product -> productMapper.toDto(product))
                .collect(Collectors.toList());

    }

    public void deleteProductInBasket(UUID id) {

        User user = userService.getCurrentUser();
        int index = -1;
        for (int i = 0; i < user.getBasket().size(); i++) {
            if (user.getBasket().get(i).getId().equals(id)) {
                index = i;
            }
        }
        if (index < 0) {
            return;
        }
        user.getBasket().remove(index);

        userService.changeUser(user);
    }
}
