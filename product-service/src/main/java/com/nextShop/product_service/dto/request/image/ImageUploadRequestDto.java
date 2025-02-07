package com.nextShop.product_service.dto.request.image;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadRequestDto {
    @NotBlank(message = "Image path cannot be blank")
    private String imagePath;
    private boolean isMainImage;
    private String productId;
    // TODO: byte[] image
}
