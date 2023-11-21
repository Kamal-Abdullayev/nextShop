package com.nextShop.product.dto.categoryDto.request;

import com.nextShop.product.entity.Product;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private String categoryName;
    private String parentCategoryId;

}

