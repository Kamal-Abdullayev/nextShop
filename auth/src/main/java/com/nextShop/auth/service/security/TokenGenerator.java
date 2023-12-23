package com.nextShop.auth.service.security;

public interface TokenGenerator<T> {
    String generate(T obj);
}
