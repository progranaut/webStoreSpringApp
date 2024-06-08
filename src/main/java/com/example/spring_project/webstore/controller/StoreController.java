package com.example.spring_project.webstore.controller;

import com.example.spring_project.security.service.SecurityUserService;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public void addProductInBasket(@PathVariable UUID id){

        storeService.addProductInBasket(id);

    }

    @GetMapping("/productInBasket")
    public List<ProductDto> getProductInCart () {

        return storeService.getProductInBasket();

    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProductInBasket(@PathVariable UUID id) {
        storeService.deleteProductInBasket(id);
    }

}
