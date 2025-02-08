package com.nextShop.order_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseResponseDto {
    private String name;
    private double price;
    private double discountPrice;
    private int quantity;

}
