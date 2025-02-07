package com.nextShop.product_service.dto.response;

import com.nextShop.product_service.model.Image;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadResponseDto {
    private String imagePath;
    private boolean isMainImage;
    private String productId;

    public static ImageUploadResponseDto convert(Image image) {
        return ImageUploadResponseDto.builder()
                .imagePath(image.getImagePath())
                .isMainImage(image.isMainImage())
                .productId(
                        image.getProduct() == null ? null : image.getProduct().getId()
                )
                .build();
    }

}
