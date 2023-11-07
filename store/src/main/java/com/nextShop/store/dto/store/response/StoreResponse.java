package com.nextShop.store.dto.store.response;


import com.nextShop.store.dto.address.response.AddressResponse;
import com.nextShop.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreResponse {
    private String name;
    private byte score;
    List<AddressResponse> addresses;

    public static StoreResponse from(Store store) {
        return StoreResponse.builder()
                .name(store.getName())
                .score(store.getScore())
                .addresses(store.getAddresses()
                        .stream()
                        .map(AddressResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
