package com.example.spring_project.webstore.mapper;

import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    //@Mapping(target = "roles", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
