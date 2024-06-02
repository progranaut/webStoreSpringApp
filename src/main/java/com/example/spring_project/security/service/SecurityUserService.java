package com.example.spring_project.security.service;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.Role;
import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.security.mapper.SecurityUserMapper;
import com.example.spring_project.security.repository.SecurityUserRepository;
import com.example.spring_project.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityUserService {

    private final SecurityUserMapper mapper;

    private final SecurityUserRepository securityUserRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public void addSecurityUser(SecurityUserDto securityUserDto) {

        List<UUID> rolesIds = securityUserDto.getRoles().stream()
                .map(RoleDto::getId)
                .toList();

        Set<Role> roles = roleService.getRoles(rolesIds);

        SecurityUser securityUserEntity = mapper.toEntity(securityUserDto);
        securityUserEntity.setRoles(roles);
        securityUserEntity.setPassword(passwordEncoder.encode(securityUserDto.getPassword()));

        securityUserRepository.save(securityUserEntity);

    }

    public SecurityUser findSecUserByName (String name) {

        return securityUserRepository.findByUsername(name)
                .orElseThrow(()->new UsernameNotFoundException("Пользователь не найден!"));

    }

}
