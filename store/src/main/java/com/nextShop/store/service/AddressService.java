package com.nextShop.store.service;

import com.nextShop.store.dto.address.request.AddressRequest;
import com.nextShop.store.dto.address.response.AddressResponse;
import com.nextShop.store.model.Address;
import com.nextShop.store.model.Store;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAddressList();
    AddressResponse getAddressById(String id);
    Address getAddressObjectById(String id);
    AddressResponse saveAddress(AddressRequest storeRequest);
    AddressResponse updateAddress(String id, AddressRequest storeRequest);
    void deactivateAddress(String id);
    void deleteAddress(String id);
    void activateAddress(String id);
    List<AddressResponse> getAllDeactivatedAddresses();
}
