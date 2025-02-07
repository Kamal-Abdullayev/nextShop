package com.nextShop.product_service.dto.response;

import com.nextShop.product_service.model.Product;
import com.nextShop.product_service.model.enums.ProductColorEnum;
import com.nextShop.product_service.model.enums.ProductSizeEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseResponseDto {
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private int quantity;

    private List<ProductColorEnum> productColorEnumList;
    private List<ProductSizeEnum> productSizeEnumList;

    public static ProductPurchaseResponseDto convert(Product product) {
        return ProductPurchaseResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .productColorEnumList(product.getColors())
                .productSizeEnumList(product.getSizes())
                .build();
    }
}
