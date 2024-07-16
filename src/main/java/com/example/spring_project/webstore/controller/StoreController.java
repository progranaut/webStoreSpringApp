package com.example.spring_project.webstore.controller;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.service.RoleService;
import com.example.spring_project.webstore.dto.*;
import com.example.spring_project.webstore.service.StoreService;
import com.example.spring_project.webstore.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final UserService userService;

    private final RoleService roleService;

    @GetMapping("/init")
    public void init() {

//        RoleDto roleDto = RoleDto.builder()
//                .roleType("ROLE_USER")
//                .build();
//
//        RoleDto roleDto1 = RoleDto.builder()
//                .roleType("ROLE_ADMIN")
//                .build();
//
//        roleService.addRole(roleDto);
//        roleService.addRole(roleDto1);

        HashSet<RoleDto> rolesDto = new HashSet<>();
        rolesDto.add(RoleDto.builder()
                        .id(UUID.fromString("c6aac0c3-3c76-4c40-a664-63a6eefea03a"))
                .build());

        UserDto userDto = UserDto.builder()
                .name("Саша")
                .securityUserDto(SecurityUserDto.builder()
                        .email("sasha@mail.ru")
                        .password("12345")
                        .roles(rolesDto)
                        .build())
                .build();

        userService.addUser(userDto);

    }

    @PostMapping("/add-in-basket/{id}")
    public ResponseEntity<?> addProductInBasket(@PathVariable UUID id){

        return storeService.addProductInBasket(id);

    }

    @PostMapping("/add-basket")
    public void addBasket(@RequestBody List<UserProductRelationDto> uprd){

        storeService.addBasket(uprd);

    }

    @GetMapping("/all-products-in-basket")
    public List<UserProductRelationDto> getProductsInCart () {

        return storeService.getAllProductsInBasket();

    }

    @GetMapping("/product-in-basket/{id}")
    public ResponseEntity<?> getProductInBasket(@PathVariable UUID id) {

        return storeService.getProductInBasket(id);

    }

    @DeleteMapping("/delete-product-from-basket/{id}")
    public ResponseEntity<?> deleteProductInBasket(@PathVariable UUID id) {
        return storeService.deleteProductFromBasket(id);
    }

    @GetMapping("/current-user-name-roll")
    public ResponseEntity<?> userName() {

        return storeService.getCurrentUserNameAndRole();

    }

    @GetMapping("/all-products")
    public List<ProductDto> allProducts() {
        return storeService.getAllProduct();
    }

    @PostMapping("/user-registration")
    public void userRegistration(@RequestBody String request, HttpServletResponse response) throws IOException {
        storeService.userRegistration(request);
        response.sendRedirect("/login");
    }

    @PostMapping("/user-registration-v2")
    public void userRegistrationV2(@RequestBody UserDto userDto, HttpServletResponse response) throws IOException {

        storeService.userRegistrationV2(userDto);

    }

    @GetMapping("/current-user")
    public UserDto getCurrentUser() {
        return storeService.getCurrentUserDto();
    }

    @GetMapping("/add-order")
    public ResponseEntity<?> addOrder() {

        return storeService.addOrder();

    }

    @GetMapping("/all-product-categories")
    public List<CategoryDto> getProductCategories(){

        return storeService.getAllProductCategories();

    }

}
