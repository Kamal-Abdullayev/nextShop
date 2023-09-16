package com.nextShop.product.dto.productDto;

import com.nextShop.product.entity.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private byte rate;
    private List<Size> sizeList;
    private List<Image> imageList;
    private List<Category> categoryList;
    private List<Color> colorList;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .discountPrice(product.getDiscountPrice())
                .description(product.getDescription())
                .rate(product.getRate())
                .sizeList(product.getSizeList())
                .imageList(product.getImageList())
                .categoryList(product.getCategoryList())
                .colorList(product.getColorList())
                .build();
    }
}
