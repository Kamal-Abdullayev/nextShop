package com.nextShop.product_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadRequestDto {
    private String imagePath;
    private boolean isMainImage;
}
