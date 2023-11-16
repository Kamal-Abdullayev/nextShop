package com.nextShop.product.dto.imageDto;

import com.nextShop.product.entity.Image;
import com.nextShop.product.entity.Product;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private String id;
    private String imagePath;
    private boolean isMainImage;

    public static ImageResponse from(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .imagePath("http://127.0.0.1:9008/next-shop.product.image/" + image.getImagePath()) // Temporary
                .isMainImage(image.isMainImage())
                .build();
    }
}
