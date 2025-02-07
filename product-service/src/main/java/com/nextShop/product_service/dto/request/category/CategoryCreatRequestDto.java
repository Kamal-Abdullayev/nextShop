package com.nextShop.product_service.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreatRequestDto {
    @NotBlank(message = "Category cannot be blank")
    @Size(min = 3, message = "Category name cannot be shorter than 3 characters")
    private String categoryName;
}
