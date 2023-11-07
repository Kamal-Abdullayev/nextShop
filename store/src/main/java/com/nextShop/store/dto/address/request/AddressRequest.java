package com.nextShop.store.dto.address.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressRequest {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private String zipCode;
    private String storeId;

}
