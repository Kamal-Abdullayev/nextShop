package com.nextShop.product.dto.categoryDto.request;

import com.nextShop.product.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateRequest {
    private String categoryName;
    private String parentCategoryId;

}
