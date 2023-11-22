package com.nextShop.auth.dto;

import com.nextShop.auth.model.Role;

import java.util.Set;

public record UserCreateRequest(
        String name,
        String surname,
        String username,
        String email,
        String phoneNumber,
        String password,
        Set<Role> authorities
) {
}
