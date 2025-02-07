package com.nextShop.product_service.exception;

import java.util.List;

public record Error(
        String errorCode,
        List<String> errorMessages
) {
}