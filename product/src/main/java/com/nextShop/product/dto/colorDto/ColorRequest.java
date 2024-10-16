package com.nextShop.product.dto.colorDto;

import com.nextShop.product.entity.Color;
import com.nextShop.product.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorRequest {
    private String color;

    public static ColorResponse from(Color color) {
        return ColorResponse.builder()
                .color(color.getColor())
                .build();
    }
}
