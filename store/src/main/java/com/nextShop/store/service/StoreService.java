package com.nextShop.store.service;

import com.nextShop.store.dto.store.request.StoreRequest;
import com.nextShop.store.dto.store.response.StoreResponse;
import com.nextShop.store.model.Store;

import java.util.List;

public interface StoreService {
    List<StoreResponse> getStoreList();
    StoreResponse getStoreById(String id);
    Store getStoreObjectById(String id);
    StoreResponse saveStore(StoreRequest storeRequest);
    StoreResponse updateStore(String id, StoreRequest storeRequest);
    void deactivateStore(String id);
    void deleteStore(String id);
    void activateStore(String id);
    List<StoreResponse> getAllDeactivatedStores();
}
