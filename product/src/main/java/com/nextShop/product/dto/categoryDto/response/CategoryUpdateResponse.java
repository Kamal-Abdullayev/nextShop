package com.nextShop.product.dto.categoryDto.response;

import com.nextShop.product.entity.Category;
import lombok.*;

import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateResponse {
    private String id;
    private String parentCategoryId;
    private String categoryName;

}
