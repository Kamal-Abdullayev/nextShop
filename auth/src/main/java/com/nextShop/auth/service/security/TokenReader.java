package com.nextShop.auth.service.security;

public interface TokenReader <T> {
    T read(String token);
}
