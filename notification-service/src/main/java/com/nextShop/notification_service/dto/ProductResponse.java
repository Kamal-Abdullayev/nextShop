package com.nextShop.notification_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private double price;
    private double discountPrice;
    private int quantity;


}
