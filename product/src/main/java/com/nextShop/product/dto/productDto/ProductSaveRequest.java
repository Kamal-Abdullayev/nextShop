package com.nextShop.product.dto.productDto;

import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.entity.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaveRequest {
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private String imageId;
    private String colorId;
    private String sizeId;
    private List<String> categoryIdList;

    public static ProductSaveRequest from(Product product) {
        return ProductSaveRequest.builder()
                .name(product.getName())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .description(product.getDescription())
                .build();
    }
}
