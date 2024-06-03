package com.example.spring_project.webstore.mapper;

import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    //@Mapping(target = "roles", ignore = true)
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

}
