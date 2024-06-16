package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.CategoryDto;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.entity.Category;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.mapper.ProductMapper;
import com.example.spring_project.webstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryService categoryService;

    public ProductDto addProduct (ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);

        product.setCategory(categoryService.toEntity(productDto.getCategoryDto()));

        product = productRepository.save(product);

        productDto = productMapper.toDto(product);

        productDto.setCategoryDto(categoryService.toDto(product.getCategory()));

        return productDto;
    }

    public List<ProductDto> getAllProduct() {

        return productRepository.findAll().stream()
                .map(product -> {
                    ProductDto productDto = productMapper.toDto(product);
                    productDto.setCategoryDto(CategoryDto.builder()
                                    .id(product.getCategory().getId())
                                    .categoryType(product.getCategory().getCategoryType())
                            .build());
                    return productDto;
                })
                .collect(Collectors.toList());

    }

    public Product findProductById(UUID id) {

        return productRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Продукт не найден!"));

    }

    public ProductDto toDto(Product product) {

        ProductDto productDto = productMapper.toDto(product);
        productDto.setCategoryDto(categoryService.toDto(product.getCategory()));

        return productDto;

    }

    public Product toEntity(ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);
        product.setCategory(categoryService.toEntity(productDto.getCategoryDto()));

        return product;

    }

    public ProductDto changeProduct(ProductDto productDto) {

        Product product = toEntity(productDto);
        //product.setCategory(categoryService.toEntity(productDto.getCategoryDto()));

        product = productRepository.save(product);

        productDto = toDto(product);
        //productDto.setCategoryDto(categoryService.toDto(product.getCategory()));

        return productDto;
    }
}
