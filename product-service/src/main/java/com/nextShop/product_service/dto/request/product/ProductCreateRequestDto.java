package com.nextShop.product_service.dto.request.product;

import com.nextShop.product_service.model.enums.ProductColorEnum;
import com.nextShop.product_service.model.enums.ProductSizeEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequestDto {
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, message = "Product name cannot be shorter than 3 characters")
    private String name;

    @NotNull
    @DecimalMin(value = "0.01", message = "Product price cannot be negative or zero")
    private double price;

    @DecimalMin(value = "0.01", message = "Product price cannot be negative or zero")
    private double discountPrice;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 10, message = "Product description cannot be shorter than 10 characters")
    private String description;

    @PositiveOrZero(message = "Product count cannot be negative")
    private int quantity;

    private List<ProductColorEnum> colors;
    private List<ProductSizeEnum> sizes;
    private Set<String> categories;
//    private List<ProductImageUploadRequestDto> images;
}
