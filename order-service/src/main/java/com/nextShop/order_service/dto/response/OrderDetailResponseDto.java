package com.nextShop.order_service.dto.response;

import com.nextShop.order_service.model.OrderDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponseDto {
    private String productId;
    private double quantity;
    private double price;
    private String orderId;

    public static OrderDetailResponseDto convert(OrderDetail orderdetail) {
        return OrderDetailResponseDto.builder()
                .quantity(orderdetail.getQuantity())
                .price(orderdetail.getPrice())
                .orderId(orderdetail.getOrder().getId())
                .build();
    }
}
