package com.example.spring_project.webstore.enums;

import com.example.spring_project.security.enums.RoleType;

import java.util.Arrays;

public enum CategoryType {

    ELECTRONICS,

    FURNITURE,

    NUTRITION;

    public static CategoryType fromString (String category) {
        return Arrays.stream(CategoryType.values())
                .filter(categoryType -> categoryType.name().equals(category))
                .findAny()
                .orElse(null);
    }
}
