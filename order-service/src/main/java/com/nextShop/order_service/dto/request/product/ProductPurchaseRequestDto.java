package com.nextShop.order_service.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseRequestDto {
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 36, message = "Product not found") // Length of UUID is 36 characters
    private String productId;

    @NotNull
    @PositiveOrZero(message = "Product count cannot be negative")
    private int quantity;


}
