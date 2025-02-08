package com.nextShop.order_service.dto.response;

import com.nextShop.order_service.dto.request.product.ProductPurchaseRequestDto;
import com.nextShop.order_service.model.Order;
import com.nextShop.order_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderResponseDto {
    private String referenceId;
    private BigDecimal totalPrice;
    private String userId;
    private PaymentMethod paymentMethod;

    public static ProductOrderResponseDto convert(Order order) {
        return ProductOrderResponseDto.builder()
                .referenceId(order.getReferenceId())
                .totalPrice(order.getTotalPrice())
                .userId(order.getUserId())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
