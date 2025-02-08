package com.nextShop.order_service.exception;

import java.util.List;

public record Error(
        String errorCode,
        List<String> errorMessages
) {
}