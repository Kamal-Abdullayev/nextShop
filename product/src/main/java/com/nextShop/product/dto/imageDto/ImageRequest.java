package com.nextShop.product.dto.imageDto;

import com.nextShop.product.entity.Image;
import com.nextShop.product.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRequest {
    private byte[] imageContent;
    private boolean isMainImage;
    private Product product;

}
