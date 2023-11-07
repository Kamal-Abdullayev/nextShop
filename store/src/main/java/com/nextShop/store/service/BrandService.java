package com.nextShop.store.service;

import com.nextShop.store.dto.brand.request.BrandRequest;
import com.nextShop.store.dto.brand.response.BrandResponse;
import com.nextShop.store.model.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getBrandList();
    BrandResponse getBrandById(String id);
    Brand getBrandObjectById(String id);
    BrandResponse saveBrand(BrandRequest brandRequest);
    BrandResponse updateBrand(String id, BrandRequest brandRequest);
    void deactivateBrand(String id);
    void deleteBrand(String id);
    void activateBrand(String id);
    List<BrandResponse> getAllDeactivatedBrands();
}
