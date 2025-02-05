package com.nextShop.product_service.dto.response;

import com.nextShop.product_service.model.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryResponseDto {
    private String categoryName;

    public static ProductCategoryResponseDto convert(Category category) {
        return ProductCategoryResponseDto.builder()
                .categoryName(category.getCategoryName())
                .build();
    }
}
