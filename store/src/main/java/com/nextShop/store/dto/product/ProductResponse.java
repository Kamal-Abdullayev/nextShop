package com.nextShop.store.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String productId;
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private byte rate;
}
