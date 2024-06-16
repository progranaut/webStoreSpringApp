package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.CategoryDto;
import com.example.spring_project.webstore.entity.Category;
import com.example.spring_project.webstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(CategoryDto categoryDto) {

        Category category = Category.builder()
                .categoryType(categoryDto.getCategoryType())
                .build();

        categoryRepository.save(category);

    }

    public List<CategoryDto> getAllProductCategories() {

        return categoryRepository.findAll().stream()
                .map(category -> CategoryDto.builder()
                        .id(category.getId())
                        .categoryType(category.getCategoryType())
                        .build())
                .collect(Collectors.toList());

    }

    public Category getCategoryByCategoryType(String categoryType) {

        return categoryRepository.findAll().stream()
                .filter(category -> category
                        .getCategoryType()
                        .equals(categoryType))
                .findFirst()
                .orElseThrow();

    }

    public Category toEntity(CategoryDto categoryDto) {

        Category category;

        if (categoryDto.getId() == null || categoryDto.getId().equals("")) {
            category = getCategoryByCategoryType(categoryDto.getCategoryType());
        } else {
            category = Category.builder()
                    .id(categoryDto.getId())
                    .build();
        }

        return category;
    }

    public CategoryDto toDto(Category category) {

        return CategoryDto.builder()
                .id(category.getId())
                .categoryType(category.getCategoryType())
                .build();

    }
}
