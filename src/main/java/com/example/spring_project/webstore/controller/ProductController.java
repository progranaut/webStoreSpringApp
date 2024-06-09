package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto) {

        productService.addProduct(productDto);

    }

}
