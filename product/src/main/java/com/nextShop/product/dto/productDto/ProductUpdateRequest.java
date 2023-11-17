package com.nextShop.product.dto.productDto;

import com.nextShop.product.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {
    private String name;
    private double price;
    private double discountPrice;
    private String description;

    public static ProductUpdateRequest from(Product product) {
        return ProductUpdateRequest.builder()
                .name(product.getName())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .description(product.getDescription())
                .build();
    }
}
