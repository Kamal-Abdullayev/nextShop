package com.nextShop.product_service.dto.request;

import com.nextShop.product_service.model.enums.ProductColorEnum;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequestDto {
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private int quantity;

    private List<ProductColorEnum> colors;
    private Set<ProductCategoryCreatRequestDto> categories;
    private List<ImageUploadRequestDto> images;
}
