package com.nextShop.product_service.dto.response;


import com.nextShop.product_service.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMainInfoResponseDto {
    private String name;
    private int quantity;

    public static ProductMainInfoResponseDto convert(Product product) {
        return ProductMainInfoResponseDto.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}
