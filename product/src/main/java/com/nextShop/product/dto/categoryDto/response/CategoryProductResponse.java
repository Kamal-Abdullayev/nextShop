package com.nextShop.product.dto.categoryDto.response;

import com.nextShop.product.entity.Category;
import com.nextShop.product.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryProductResponse {
    private String id;
    private List<Product> productList;

    public static CategoryProductResponse from(Category from) {
        return CategoryProductResponse.builder()
                .id(from.getId())
                .productList(from.getProductList())
                .build();
    }
}
