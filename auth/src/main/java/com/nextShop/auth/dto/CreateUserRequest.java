package com.nextShop.auth.dto;

import com.nextShop.auth.models.Role;

import java.util.Set;

public record CreateUserRequest(
        String name,
        String surname,
        String username,
        String email,
        String password,
        String phoneNumber,
        Set<Role> authorities
) {

}
