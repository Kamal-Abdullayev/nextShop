package com.nextShop.order_service.dto.request.payment;

import com.nextShop.order_service.dto.response.UserResponseDto;
import com.nextShop.order_service.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {
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
