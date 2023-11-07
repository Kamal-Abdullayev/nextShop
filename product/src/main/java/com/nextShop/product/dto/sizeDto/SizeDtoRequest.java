package com.nextShop.product.dto.sizeDto;

import com.nextShop.product.entity.Product;
import com.nextShop.product.entity.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDtoRequest {
    private String size;
    private Product product;

    public static SizeDtoRequest from(Size size) {
        return SizeDtoRequest.builder()
                .size(size.getSize())
                .product(size.getProduct())
                .build();
    }
}
