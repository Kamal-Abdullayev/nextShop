package com.nextShop.store.service.impl;

import com.nextShop.store.dto.address.request.AddressRequest;
import com.nextShop.store.dto.address.response.AddressResponse;
import com.nextShop.store.exceprion.CommonErrorCodesException;
import com.nextShop.store.exceprion.ItemCannotBeNullException;
import com.nextShop.store.exceprion.ItemNotFoundException;
import com.nextShop.store.model.Address;
import com.nextShop.store.model.Store;
import com.nextShop.store.model.base.BaseEntityAudit;
import com.nextShop.store.repository.AddressRepository;
import com.nextShop.store.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    @Override
    public List<AddressResponse> getAddressList() {
        return addressRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(AddressResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getAddressById(String id) {
        Address address = findAddress(id);
        if (!address.isActive()) {
            throw new ItemNotFoundException("Address");
        }
        return AddressResponse.from(address);
    }

    @Override
    public Address getAddressObjectById(String id) {
        Address address = findAddress(id);
        if (!address.isActive()) {
            System.out.println("Not found!!!");
            throw new ItemNotFoundException("Address");
        }
        return address;
    }
    @Transactional
    @Override
    public AddressResponse saveAddress(AddressRequest addressRequestDto) {
        Address address = new Address(addressRequestDto.getCountry(), addressRequestDto.getCity(),
                addressRequestDto.getAddress(), addressRequestDto.getZipCode());
        addressRepository.save(address);
        return AddressResponse.from(address);
    }

    @Override
    public AddressResponse updateAddress(String id, AddressRequest addressRequestDto) {
        Address dbAddress = findAddress(id);
        if (!dbAddress.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(addressRequestDto.getCountry()) && !addressRequestDto.getCountry().isEmpty()) {
            dbAddress.setCountry(addressRequestDto.getCountry());
        } else {
            throw new ItemCannotBeNullException("Country field");
        }
        if (Objects.nonNull(addressRequestDto.getCity()) && !addressRequestDto.getCity().isEmpty()) {
            dbAddress.setCity(addressRequestDto.getCity());
        } else {
            throw new ItemCannotBeNullException("City field");
        }
        if (Objects.nonNull(addressRequestDto.getAddress()) && !addressRequestDto.getAddress().isEmpty()) {
            dbAddress.setAddress(addressRequestDto.getAddress());
        } else {
            throw new ItemCannotBeNullException("Address field");
        }
        if (Objects.nonNull(addressRequestDto.getZipCode()) && !addressRequestDto.getZipCode().isEmpty()) {
            dbAddress.setZipCode(addressRequestDto.getZipCode());
        } else {
            throw new ItemCannotBeNullException("Zip code field");
        }
        addressRepository.save(dbAddress);
        return AddressResponse.from(dbAddress);
    }

    @Override
    public void deactivateAddress(String id) {
        Address address = findAddress(id);
        if (!address.isActive()) throw new ItemNotFoundException("Address");
        address.setActive(false);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(String id) {
        Address address = findAddress(id);
        if (address.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        addressRepository.delete(address);
    }

    @Override
    public void activateAddress(String id) {
        Address address = findAddress(id);
        if (address.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        address.setActive(true);
        addressRepository.save(address);
    }

    @Override
    public List<AddressResponse> getAllDeactivatedAddresses() {
        return addressRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(AddressResponse::from)
                .collect(Collectors.toList());
    }

    private Address findAddress(String id) {
        return addressRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Address"));
    }
}
