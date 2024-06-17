package com.example.spring_project.webstore.service;

import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.Role;
import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.security.mapper.SecurityUserMapper;
import com.example.spring_project.security.service.SecurityUserService;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.dto.UserNameAndRoleDto;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.UserMapper;
import com.example.spring_project.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final SecurityUserService securityUserService;

    private final UserMapper userMapper;

    private final SecurityUserMapper securityUserMapper;

    public void addUser (UserDto userDto) {

        SecurityUserDto securityUserDto = userDto.getSecurityUserDto();
        securityUserService.addSecurityUser(securityUserDto);

        SecurityUser securityUser = securityUserService
                .findSecUserByName(securityUserDto.getEmail());

        User user = userMapper.toEntity(userDto);
        user.setSecurityUser(securityUser);

        System.out.println(user.getEmail());

        userRepository.save(user);
        
    }

    public void changeUser(UserDto userDto){

        SecurityUserDto securityUserDto = userDto.getSecurityUserDto();
        SecurityUser securityUser = securityUserMapper.toEntity(securityUserDto);

        User user = userMapper.toEntity(userDto);
        user.setSecurityUser(securityUser);

        changeUser(user);
    }

    public void changeUser(User user) {
        userRepository.save(user);
    }

    public User getUserBySecId (UUID id) {
        return userRepository.findUserBySecId(id)
                .orElseThrow(()-> new UsernameNotFoundException("Пользователь с таким id не найден!"));
    }

    public User getCurrentUser() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal == null) {
            return null;
        }

        if (!(principal instanceof UserDetails)) {
            return null;
        }

        UserDetails userDetails = (UserDetails) principal;
        SecurityUser securityUser = securityUserService
                .findSecUserByName(userDetails.getUsername());

        User user = this.getUserBySecId(securityUser.getId());

        return user;

    }

    public /*UserNameAndRoleDto*/ ResponseEntity<?> getCurrentUserNameAndRole() {

        User user = getCurrentUser();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Role> roles = user.getSecurityUser().getRoles();
        Set<RoleDto> roleDtoSet = roles.stream().map(role -> RoleDto.builder()
                .roleType(role.getRoleType().toString())
                .build()).collect(Collectors.toSet());

        return new ResponseEntity<>(UserNameAndRoleDto.builder()
                .name(user.getName())
                .roles(roleDtoSet)
                .build(), HttpStatus.OK);

    }

    public UserDto getCurrentUserDto() {

        User user = getCurrentUser();

        UserDto userDto = userMapper.toDto(user);
        userDto.setSecurityUserDto(SecurityUserDto.builder()
                        .id(user.getSecurityUser().getId())
                .build());

        return userDto;

    }

    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());

    }
}
