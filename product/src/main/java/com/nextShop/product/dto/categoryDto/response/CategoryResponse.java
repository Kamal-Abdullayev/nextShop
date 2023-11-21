package com.nextShop.product.dto.categoryDto.response;

import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.entity.Category;
import com.nextShop.product.entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String categoryName;
    private ParentCategoryResponse parentCategory;
    private List<ParentCategoryResponse> categoryChildrensList;
    private List<ProductResponse> productList;

    public static CategoryResponse from(Category category) {
        ParentCategoryResponse parentCategoryResponse = null;
        List<ParentCategoryResponse> categoryChildrensList = new LinkedList<>();
        if (category.getParentCategory() != null) {
            parentCategoryResponse = ParentCategoryResponse.from(category.getParentCategory());
        }
        if (category.getCategoryChildrensList() != null) {
            categoryChildrensList = category.getCategoryChildrensList().stream().map(ParentCategoryResponse::from).collect(Collectors.toList());
        }

        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .parentCategory(parentCategoryResponse)
                .categoryChildrensList(categoryChildrensList)
                .productList(category.getProductList().stream()
                        .map(ProductResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
