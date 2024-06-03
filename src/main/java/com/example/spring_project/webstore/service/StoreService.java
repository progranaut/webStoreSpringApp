package com.example.spring_project.webstore.service;

import com.example.spring_project.security.entity.SecurityUser;
import com.example.spring_project.security.service.SecurityUserService;
import com.example.spring_project.webstore.dto.ProductDto;
import com.example.spring_project.webstore.entity.Product;
import com.example.spring_project.webstore.entity.User;
import com.example.spring_project.webstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final SecurityUserService securityUserService;

    private final UserService userService;

    private final ProductMapper productMapper;

    private final ProductService productService;

    public void addProductInBasket(ProductDto productDto) {

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserDetails)) {
            return;
        }

        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        SecurityUser securityUser = securityUserService.findSecUserByName(username);

        User user = userService.getUserBySecId(securityUser.getId());

        //Product product = productMapper.toEntity(productDto);
        Product product = productService.findProductById(productDto.getId());

        user.getBasket().add(product);

        userService.changeUser(user);

    }

}
