package com.nextShop.product.dto.categoryDto.response;


import com.nextShop.product.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentCategoryResponse {
    private String id;
    private String categoryName;

    public static ParentCategoryResponse from(Category parentCategory) {
        return ParentCategoryResponse.builder()
                .id(parentCategory.getId())
                .categoryName(parentCategory.getCategoryName())
                .build();
    }

}
