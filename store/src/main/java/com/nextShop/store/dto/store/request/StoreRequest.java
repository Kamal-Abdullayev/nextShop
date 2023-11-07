package com.nextShop.store.dto.store.request;

import com.nextShop.store.dto.address.request.AddressRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreRequest {
    @NotBlank
    private String name;
    private String brandId;
    @NotNull
    private String addressId;

}
