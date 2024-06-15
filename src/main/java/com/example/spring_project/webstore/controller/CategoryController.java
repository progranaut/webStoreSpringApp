package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.dto.CategoryDto;
import com.example.spring_project.webstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto categoryDto) {

        categoryService.addCategory(categoryDto);

    }

}
