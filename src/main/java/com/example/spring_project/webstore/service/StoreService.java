package com.example.spring_project.webstore.service;

import com.example.spring_project.messagebot.service.MessageService;
import com.example.spring_project.security.dto.RoleDto;
import com.example.spring_project.security.dto.SecurityUserDto;
import com.example.spring_project.security.service.RoleService;
import com.example.spring_project.webstore.dto.*;
import com.example.spring_project.webstore.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserService userService;

    private final ProductService productService;

    private final RoleService roleService;

    private final UserProductRelationService userProductRelationService;

    private final OrderService orderService;

    private final OrderProductRelationService orderProductRelationService;

    private final CategoryService categoryService;

    private final MessageService messageService;

    public ResponseEntity<?> addProductInBasket(UUID id) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productService.findProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return userProductRelationService.addUserProductRelation(user, product);
    }

    public ResponseEntity<?> deleteProductFromBasket(UUID id) {

        User user = userService.getCurrentUser();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productService.findProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return userProductRelationService.delUserProductRelation(user, product);
    }

    public List<UserProductRelationDto> getAllProductsInBasket() {

        User user = userService.getCurrentUser();

        if (user == null) {
            return null;
        }

        return userProductRelationService.getRelations(user).stream()
                .map(userProductRelation -> UserProductRelationDto.builder()
                        .productDto(productService.toDto(userProductRelation.getProduct()))
                        .quantity(userProductRelation.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getCurrentUserNameAndRole() {
        return userService.getCurrentUserNameAndRole();
    }

    public List<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    public void userRegistration(String request) {

        String[] namePass = request.split("&");

        HashSet<RoleDto> roleDtos = new HashSet<>();
        roleDtos.add(RoleDto.builder()
                        .id(UUID.fromString("24440326-f4e5-4db6-a351-a116a00320d8"))
                .build());

        userService.addUser(UserDto.builder()
                        .name(namePass[0].split("=")[1])
                        .email(namePass[1].split("=")[1])
                        .securityUserDto(SecurityUserDto.builder()
                                .email(namePass[1].split("=")[1])
                                .password(namePass[2].split("=")[1])
                                .roles(roleDtos)
                                .build())
                .build());
    }

    public void userRegistrationV2(UserDto userDto) {

        userDto.getSecurityUserDto().setRoles(roleService.getUserRole().stream()
                .map(role -> RoleDto.builder()
                        .id(role.getId())
                        .roleType(role.getRoleType().toString())
                        .build())
                .collect(Collectors.toSet()));

        System.out.println(userDto);

        userService.addUser(userDto);
    }

    public UserDto getCurrentUserDto() {
        return userService.getCurrentUserDto();
    }

    public ResponseEntity<?> getProductInBasket(UUID productId) {

        User user = userService.getCurrentUser();

        try {

            UserProductRelation userProductRelation = userProductRelationService.getRelation(user.getId(), productId);

            return new ResponseEntity<>(
                    UserProductRelationDto.builder()
                    .productDto(productService.toDto(userProductRelation.getProduct()))
                    .quantity(userProductRelation.getQuantity())
                    .build(),
                    HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    public ResponseEntity<?> addOrder() {

        User user = userService.getCurrentUser();

        if (user.getName() == null || user.getName().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getPhoneNumber() == null || user.getPhoneNumber().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println(user.getEmail());
        if (user.getEmail() == null || user.getEmail().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getAddress() == null || user.getAddress().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Order order = orderService.addOrder(user);

        List<UserProductRelation> userProductRelations = userProductRelationService
                .getRelations(user);

        List<OrderProductRelation> orderProductRelations = userProductRelations.stream()
                .map(relation -> OrderProductRelation.builder()
                        .order(order)
                        .product(relation.getProduct())
                        .relationQuantity(relation.getQuantity())
                        .relationPrice(relation.getProduct().getPrice())
                        .build())
                .collect(Collectors.toList());

        orderProductRelationService.addRelations(orderProductRelations);

        List<Product> products = productService.findProductsById(userProductRelations.stream()
                .map(relation -> relation.getProduct().getId())
                .collect(Collectors.toList()));

        userProductRelations.stream().forEach(relation -> {
            Product product = products.stream()
                    .filter(prod -> relation.getProduct().getId().equals(prod.getId()))
                    .findFirst().orElseThrow();
            product.setAvailability(product.getAvailability() - relation.getQuantity());
        });

        productService.changeProducts(products);

        userProductRelationService.delAllUserProductRelation(userProductRelations);

        messageService.sendOrderInfo(user, order, orderProductRelations);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<CategoryDto> getAllProductCategories() {
        return categoryService.getAllProductCategories();
    }

    public void addBasket(List<UserProductRelationDto> uprd) {

        User user = userService.getCurrentUser();

        List<UserProductRelation> userProductRelations = userProductRelationService.getRelations(user);

        userProductRelationService.delAllUserProductRelation(userProductRelations);

        userProductRelations = uprd.stream()
                .map(relation ->
                  UserProductRelation.builder()
                          .user(user)
                          .product(productService.toEntity(relation.getProductDto()))
                          .quantity(relation.getQuantity())
                          .build()
                  ).collect(Collectors.toList());

        userProductRelationService.addAllRelation(userProductRelations);
    }
}
