package com.nextShop.user_service.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomErrorResponse {
    private final Error error;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public CustomErrorResponse(Error error) {
        this.error = error;
    }
}