package com.example.spring_project.security.mapper;

import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SecurityUserMapper {

    @Mapping(target = "roles", ignore = true)
    SecurityUserDto toDto(SecurityUser securityUser);

    SecurityUser toEntity(SecurityUserDto securityUserDto);
}
