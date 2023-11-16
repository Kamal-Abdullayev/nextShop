package com.nextShop.store.dto.brand.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private byte[] logo;
    @NotBlank
    private String storeId;

}
