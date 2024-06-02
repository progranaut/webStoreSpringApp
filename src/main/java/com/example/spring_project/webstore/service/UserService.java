package com.example.spring_project.webstore.service;

import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.security.service.SecurityUserService;
import com.example.spring_project.webstore.dto.UserDto;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final SecurityUserService securityUserService;

    public void addUser (UserDto userDto) {

        SecurityUserDto securityUserDto = userDto.getSecurityUserDto();
        securityUserService.addSecurityUser(securityUserDto);

        SecurityUser securityUser = securityUserService
                .findSecUserByName(securityUserDto.getEmail());

        User user = User.builder()
                .securityUser(securityUser)
                .build();

        userRepository.save(user);
        
    }

}
