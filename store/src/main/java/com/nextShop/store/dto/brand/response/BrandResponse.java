package com.nextShop.store.dto.brand.response;

import com.nextShop.store.dto.store.response.StoreResponse;
import com.nextShop.store.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    private String name;
    private String description;
    private byte[] logo;
    private Set<StoreResponse> stores;

    public static BrandResponse from(Brand brand) {
        return BrandResponse.builder()
                .name(brand.getName())
                .description(brand.getDescription())
                .logo(brand.getLogo())
                .stores(brand.getStores().stream().map(StoreResponse::from).collect(Collectors.toSet()))
                .build();
    }
}
