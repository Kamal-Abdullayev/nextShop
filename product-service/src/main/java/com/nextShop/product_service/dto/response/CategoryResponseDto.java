package com.nextShop.product_service.dto.response;

import com.nextShop.product_service.model.Category;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private String categoryName;
    private Set<ProductMainInfoResponseDto> products;

    public static CategoryResponseDto convert(Category category) {
        return CategoryResponseDto.builder()
                .categoryName(category.getCategoryName())
                .products(category.getProducts() == null ? new HashSet<>() :
                        category.getProducts().stream()
                                .map(ProductMainInfoResponseDto::convert)
                                .collect(Collectors.toSet()))
                .build();
    }
}
