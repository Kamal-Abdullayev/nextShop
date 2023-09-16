package com.nextShop.product.dto.converter;

import com.nextShop.product.dto.colorDto.ColorRequest;
import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.entity.Color;

public class ColorDtoConverter {
    public static Color convertToColor(ColorResponse colorResponse) {
            return Color.builder()
                    .color(colorResponse.getColor())
                    .product(colorResponse.getProduct())
                    .build();
    }
}
