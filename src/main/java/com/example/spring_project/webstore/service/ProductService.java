package com.example.spring_project.webstore.service;

import com.example.spring_project.webstore.dto.CategoryDto;
import com.example.spring_project.webstore.dto.ProductDto;
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

    public void addProduct (ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);

        productRepository.save(product);

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

        return productMapper.toDto(product);

    }

    public Product toEntity(ProductDto productDto) {

        return productMapper.toEntity(productDto);

    }
}
