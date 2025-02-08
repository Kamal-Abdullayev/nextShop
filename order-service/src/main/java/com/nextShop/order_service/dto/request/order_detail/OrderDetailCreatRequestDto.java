package com.nextShop.order_service.dto.request.order_detail;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailCreatRequestDto {
    private String productName;
    private double quantity;
    private double price;
    private double discountPrice;
    private String orderId;
}
