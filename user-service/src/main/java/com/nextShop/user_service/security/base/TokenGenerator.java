package com.nextShop.user_service.security.base;

public interface TokenGenerator <T> {

    String generate(T obj);

}
