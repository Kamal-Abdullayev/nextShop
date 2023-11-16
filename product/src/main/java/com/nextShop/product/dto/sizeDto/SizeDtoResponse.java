package com.nextShop.product.dto.sizeDto;

import com.nextShop.product.entity.Product;
import com.nextShop.product.entity.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDtoResponse {
    private String id;
    private String size;
    private Product product;
    public static SizeDtoResponse from(Size size) {
        return SizeDtoResponse.builder()
                .id(size.getId())
                .size(size.getSize())
                .build();
    }
}
