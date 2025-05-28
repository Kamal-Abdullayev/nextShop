package com.nextShop.user_service.security.base;

public interface TokenReader <T> {

    T read(String token);

}
