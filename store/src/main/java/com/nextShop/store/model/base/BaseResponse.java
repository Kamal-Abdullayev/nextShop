package com.nextShop.store.model.base;

import lombok.*;
import org.springframework.http.HttpStatus;


@Builder
public class BaseResponse<T> {
    HttpStatus status;
    String message;
    T data;

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .message("Success")
                .data(data)
                .build();
    }

    public BaseResponse() {
    }

    public BaseResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
