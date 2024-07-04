package com.example.spring_project.security.service;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.entity.Role;
import com.example.spring_project.security.enums.RoleType;
import com.example.spring_project.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void addRole(RoleDto roleDto) {
        Role role = Role.builder()
                .roleType(RoleType.fromString(roleDto.getRoleType()))
                .build();
        roleRepository.save(role);
    }

    public Set<Role> getRoles(List<UUID> rolesIds) {

        return rolesIds.stream()
                .map(roleRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toSet());

    }

    public Set<Role> getUserRole() {

        return roleRepository.findAll().stream()
                .filter(role -> role.getRoleType().toString().equals("ROLE_USER"))
                .collect(Collectors.toSet());

    }

}
