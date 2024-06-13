package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.dto.UserProductRelationDto;
import com.example.spring_project.webstore.service.StoreService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

//    @PostMapping("/add-in-basket")
//    public void addProductInBasket(@RequestBody ProductDto productDto){
//
//        storeService.addProductInBasket(productDto);
//
//    }

    @PostMapping("/add-in-basket/{id}")
    public ResponseEntity<?> addProductInBasket(@PathVariable UUID id){

        return storeService.addProductInBasket(id);

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
    public void deleteProductInBasket(@PathVariable UUID id) {
        storeService.deleteProductFromBasket(id);
    }

    @GetMapping("/current-user-name")
    public String userName() {

        return storeService.getCurrentUserName();

    }

    @GetMapping("/products-all")
    public List<ProductDto> allProducts() {
        return storeService.getAllProduct();
    }

    @PostMapping("/user-registration")
    public void userRegistration(@RequestBody String request, HttpServletResponse response) throws IOException {
        storeService.userRegistration(request);
        response.sendRedirect("/login");
    }

    @GetMapping("/current-user")
    public UserDto getCurrentUser() {
        return storeService.getCurrentUserDto();
    }

}
