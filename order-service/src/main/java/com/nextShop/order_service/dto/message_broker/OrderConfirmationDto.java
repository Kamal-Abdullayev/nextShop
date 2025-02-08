package com.nextShop.order_service.dto.message_broker;

import com.nextShop.order_service.dto.response.ProductPurchaseResponseDto;
import com.nextShop.order_service.dto.response.UserResponseDto;
import com.nextShop.order_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderConfirmationDto {
    private String referenceId;
    private BigDecimal orderAmount;
    private String userId;
    private PaymentMethod paymentMethod;
    private UserResponseDto user;

    List<ProductPurchaseResponseDto> products;
}
