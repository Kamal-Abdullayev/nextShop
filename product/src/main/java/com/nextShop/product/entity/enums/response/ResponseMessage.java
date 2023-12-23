package com.nextShop.product.entity.enums.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {

    String key();
    String message();
    HttpStatus status();

}
