package com.nextShop.product.entity.enums.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SuccessResponseMessage implements ResponseMessage {
    SUCCESS("success", "Successfully", HttpStatus.OK),
    CREATED("create", "Successfully created", HttpStatus.CREATED);

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
