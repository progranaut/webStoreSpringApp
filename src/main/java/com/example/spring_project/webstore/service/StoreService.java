package com.example.spring_project.webstore.service;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.service.RoleService;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.UserProductRelation;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

        //userProductRelationService.addProductQuantity(userProductRelation);

//        System.out.println(user);
//        System.out.println(product);

//        var list = user.getProductQuantitySet().stream()
//                .filter(productQuantity -> productQuantity.getProduct()
//                        .getId().equals(product.getId()))
//                .collect(Collectors.toList());
//
//        if (list.isEmpty()) {
//            user.addProductQuantity(ProductQuantity.builder()
//                            .product(product)
//                            .user(user)
//                            .quantity(2)
//                            .build());
//        } else {
//            int tmp = list.get(0).getQuantity();
//            tmp++;
//            list.get(0).setQuantity(tmp);
//        }

        //user.getBasket().add(product);
        //userService.changeUser(user);

    }

//    public List<ProductDto> getProductInBasket() {
//
//        User user = userService.getCurrentUser();
//
//        if (user == null) {
//            return null;
//        }
//
//        return user.getBasket().stream()
//                .map(product -> productMapper.toDto(product))
//                .collect(Collectors.toList());
//
//    }

//    public void deleteProductInBasket(UUID id) {
//
//        User user = userService.getCurrentUser();
//        int index = -1;
//        for (int i = 0; i < user.getBasket().size(); i++) {
//            if (user.getBasket().get(i).getId().equals(id)) {
//                index = i;
//            }
//        }
//        if (index < 0) {
//            return;
//        }
//        user.getBasket().remove(index);
//
//        userService.changeUser(user);
//    }

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
}
