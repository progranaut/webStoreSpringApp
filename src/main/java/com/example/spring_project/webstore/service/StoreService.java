package com.example.spring_project.webstore.service;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.service.RoleService;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.dto.UserProductRelationDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.UserProductRelation;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;

    private final ProductMapper productMapper;

    private final ProductService productService;

    private final RoleService roleService;

    private final UserProductRelationService userProductRelationService;

    public void addProductInBasket(UUID id) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return;
        }

        Product product = productService.findProductById(id);

        if (product == null) {
            return;
        }

        userProductRelationService.addUserProductRelation(user, product);

    }

    public void deleteProductFromBasket(UUID id) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return;
        }

        Product product = productService.findProductById(id);

        if (product == null) {
            return;
        }

        userProductRelationService.delUserProductRelation(user, product);

    }

    public List<UserProductRelationDto> getAllProductsInBasket() {

        User user = userService.getCurrentUser();

        if (user == null) {
            return null;
        }

        return userProductRelationService.getRelations(user).stream()
                .map(userProductRelation -> UserProductRelationDto.builder()
                        .productDto(productMapper.toDto(userProductRelation.getProduct()))
                        .quantity(userProductRelation.getQuantity())
                        .build())
                .collect(Collectors.toList());

    }

    public String getCurrentUserName() {
        return userService.getCurrentUserName();
    }

    public List<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    public void userRegistration(String request) {

        String[] namePass = request.split("&");

        HashSet<RoleDto> roleDtos = new HashSet<>();
        roleDtos.add(RoleDto.builder()
                        .id(UUID.fromString("24440326-f4e5-4db6-a351-a116a00320d8"))
                .build());

        System.out.println(namePass[0].split("=")[1]);

        userService.addUser(UserDto.builder()
                        .name(namePass[0].split("=")[1])
                        .email(namePass[1].split("=")[1])
                        .securityUserDto(SecurityUserDto.builder()
                                .email(namePass[1].split("=")[1])
                                .password(namePass[2].split("=")[1])
                                .roles(roleDtos)
                                .build())
                .build());

    }

    public UserDto getCurrentUserDto() {

        return userService.getCurrentUserDto();

    }

    public ResponseEntity<?> getProductInBasket(UUID productId) {

        User user = userService.getCurrentUser();

        try {

            UserProductRelation userProductRelation = userProductRelationService.getRelation(user.getId(), productId);

            return new ResponseEntity<>(
                    UserProductRelationDto.builder()
                    .productDto(productMapper.toDto(userProductRelation.getProduct()))
                    .quantity(userProductRelation.getQuantity())
                    .build(),
                    HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }
}
