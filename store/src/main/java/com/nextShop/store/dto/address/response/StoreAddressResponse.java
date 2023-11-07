package com.nextShop.store.dto.address.response;

import com.nextShop.store.dto.store.response.StoreResponse;
import com.nextShop.store.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreAddressResponse {
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private StoreResponse store;

}
