package com.nextShop.auth.dto;

public record AuthRequest(
        String username,
        String phoneNumber
) {
}
