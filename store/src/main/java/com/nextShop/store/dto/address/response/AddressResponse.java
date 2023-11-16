package com.nextShop.store.dto.address.response;

import com.nextShop.store.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressResponse {
    private String country;
    private String city;
    private String address;
    private String zipCode;

    private String id; // temporary
    public static AddressResponse from(Address newAddress) {
        return AddressResponse.builder()
                .id(newAddress.getId())
                .country(newAddress.getCountry())
                .city(newAddress.getCity())
                .address(newAddress.getAddress())
                .zipCode(newAddress.getZipCode())
                .build();
    }
}
