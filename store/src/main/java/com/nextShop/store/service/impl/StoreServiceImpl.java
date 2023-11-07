package com.nextShop.store.service.impl;

import com.nextShop.store.dto.store.request.StoreRequest;
import com.nextShop.store.dto.store.response.StoreResponse;
import com.nextShop.store.exceprion.CommonErrorCodesException;
import com.nextShop.store.exceprion.ItemCannotBeNullException;
import com.nextShop.store.exceprion.ItemNotFoundException;
import com.nextShop.store.model.Address;
import com.nextShop.store.model.Brand;
import com.nextShop.store.model.Store;
import com.nextShop.store.model.base.BaseEntityAudit;
import com.nextShop.store.repository.StoreRepository;
import com.nextShop.store.service.AddressService;
import com.nextShop.store.service.BrandService;
import com.nextShop.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final BrandService brandService;
    private final AddressService addressService;

    @Override
    public List<StoreResponse> getStoreList() {
        return storeRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(StoreResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public StoreResponse getStoreById(String id) {
        Store store = findStore(id);
        if (!store.isActive()) {
            throw new ItemNotFoundException("Store");
        }
        return StoreResponse.from(store);
    }

    @Override
    public Store getStoreObjectById(String id) {
        Store store = findStore(id);
        if (!store.isActive()) {
            throw new ItemNotFoundException("Store");
        }
        return store;
    }

    @Override
    public StoreResponse saveStore(StoreRequest storeRequestDto) {
        Address address = addressService.getAddressObjectById(storeRequestDto.getAddressId());
        Store store = new Store(storeRequestDto.getName(), List.of(address));
        storeRepository.save(store);
        return StoreResponse.from(store);
    }

    @Override
    public StoreResponse updateStore(String id, StoreRequest storeRequestDto) {
        Store dbStore = findStore(id);
        if (!dbStore.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(storeRequestDto.getName()) && !storeRequestDto.getName().isEmpty()) {
            dbStore.setName(storeRequestDto.getName());
        } else {
            throw new ItemCannotBeNullException("Name field");
        }
        return StoreResponse.from(dbStore);
    }

    @Override
    public void deactivateStore(String id) {
        Store store = findStore(id);
        if (!store.isActive()) throw new ItemNotFoundException("Store");
        store.setActive(false);
        storeRepository.save(store);
    }

    @Override
    public void deleteStore(String id) {
        Store store = findStore(id);
        if (store.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        storeRepository.delete(store);
    }

    @Override
    public void activateStore(String id) {
        Store store = findStore(id);
        if (store.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        store.setActive(true);
        storeRepository.save(store);
    }

    @Override
    public List<StoreResponse> getAllDeactivatedStores() {
        return storeRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(StoreResponse::from)
                .collect(Collectors.toList());
    }


    private Store findStore(String id) {
        return storeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Store"));
    }

}
