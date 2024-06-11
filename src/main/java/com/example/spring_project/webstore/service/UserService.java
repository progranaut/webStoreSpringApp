package com.example.spring_project.webstore.service;

import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.security.mapper.SecurityUserMapper;
import com.example.spring_project.security.service.SecurityUserService;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.UserMapper;
import com.example.spring_project.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        if (!(principal instanceof UserDetails)) {
            return null;
        }

        UserDetails userDetails = (UserDetails) principal;
        SecurityUser securityUser = securityUserService
                .findSecUserByName(userDetails.getUsername());

        User user = this.getUserBySecId(securityUser.getId());

        return user;

    }

    public String getCurrentUserName() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            SecurityUser securityUser = securityUserService
                    .findSecUserByName(userDetails.getUsername());

            User user = this.getUserBySecId(securityUser.getId());

            return user.getName();
        }

        return null;
    }

    public UserDto getCurrentUserDto() {
        User user = getCurrentUser();
        UserDto userDto = userMapper.toDto(user);
        userDto.setSecurityUserDto(SecurityUserDto.builder()
                        .id(user.getSecurityUser().getId())
                .build());
        return userDto;
    }
}
