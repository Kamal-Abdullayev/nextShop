package com.nextShop.payment_service.dto;

import com.nextShop.payment_service.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    @NotBlank(message = "Order id cannot be blank")
    private String orderId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull(message = "Order reference cannot be blank")
    private String orderReference;
    private UserResponseDto user;
}
