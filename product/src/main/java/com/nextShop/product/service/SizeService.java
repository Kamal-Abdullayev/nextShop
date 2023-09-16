package com.nextShop.product.service;

import com.nextShop.product.dto.size.SizeDtoRequest;
import com.nextShop.product.dto.size.SizeDtoResponse;

import java.util.List;

public interface SizeService {
    List<SizeDtoResponse> getSizeList();
    SizeDtoResponse getSizeById(String id);
    String saveSize(SizeDtoRequest sizeDtoRequest);
    SizeDtoResponse updateSize(String id, SizeDtoRequest sizeDtoRequest);
    void deactivateSize(String id);
    void deleteSize(String id);
    void activateSize(String id);
    List<SizeDtoResponse> getAllDeactivatedSizes();
}
