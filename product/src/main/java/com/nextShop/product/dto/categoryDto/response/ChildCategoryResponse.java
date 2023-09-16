package com.nextShop.product.dto.categoryDto.response;

import com.nextShop.product.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildCategoryResponse {
    private String id;
    private String categoryName;

    public static ChildCategoryResponse from(Category childCategory) {
        return ChildCategoryResponse.builder()
                .id(childCategory.getId())
                .categoryName(childCategory.getCategoryName())
                .build();
    }
}
