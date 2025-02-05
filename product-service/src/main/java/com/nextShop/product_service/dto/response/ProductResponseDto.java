package com.nextShop.product_service.dto.response;

import com.nextShop.product_service.model.Product;
import com.nextShop.product_service.model.enums.ProductColorEnum;
import com.nextShop.product_service.model.enums.ProductSizeEnum;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private int quantity;

    private List<ProductColorEnum> productColorEnumList;
    private List<ProductSizeEnum> productSizeEnumList;
    private Set<ProductCategoryResponseDto> categories;
    private List<ImageUploadResponseDto> images;

    public static ProductResponseDto convert(Product product) {
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .productColorEnumList(product.getColors())
                .productSizeEnumList(product.getSizes())
                .categories(
                        product.getCategories().stream()
                                .map(ProductCategoryResponseDto::convert)
                                .collect(Collectors.toSet())
                )
                .images(product.getImages().stream()
                        .map(ImageUploadResponseDto::convert)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
