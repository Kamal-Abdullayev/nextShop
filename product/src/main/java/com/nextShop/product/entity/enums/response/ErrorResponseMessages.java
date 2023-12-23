package com.nextShop.product.entity.enums.response;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorResponseMessages implements ResponseMessage {
    UNEXPECTED("unexpected", "Unexpected error happens", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found", "Entity not found", HttpStatus.NOT_FOUND);

    private final String key;
    private final String message;
    private final HttpStatus status;


    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
