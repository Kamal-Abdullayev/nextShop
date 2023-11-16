package com.nextShop.store.service.impl;

import com.nextShop.store.dto.brand.request.BrandRequest;
import com.nextShop.store.dto.brand.response.BrandResponse;
import com.nextShop.store.exceprion.CommonErrorCodesException;
import com.nextShop.store.exceprion.ItemCannotBeNullException;
import com.nextShop.store.exceprion.ItemNotFoundException;
import com.nextShop.store.model.Brand;
import com.nextShop.store.model.Store;
import com.nextShop.store.model.base.BaseEntityAudit;
import com.nextShop.store.repository.BrandRepository;
import com.nextShop.store.service.BrandService;
import com.nextShop.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final StoreService storeService;

    @Override
    public List<BrandResponse> getBrandList() {
        return brandRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(BrandResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponse getBrandById(String id) {
        Brand brand = findBrand(id);
        if (!brand.isActive()) {
            throw new ItemNotFoundException("Brand");
        }
        return BrandResponse.from(brand);
    }

    @Override
    public Brand getBrandObjectById(String id) {
        Brand brand = findBrand(id);
        if (!brand.isActive()) {
            throw new ItemNotFoundException("Brand");
        }
        return brand;
    }

    @Override
    public BrandResponse saveBrand(BrandRequest brandRequestDto) {
        Store store = storeService.getStoreObjectById(brandRequestDto.getStoreId());
        Brand brand = new Brand(brandRequestDto.getName(), brandRequestDto.getDescription(), brandRequestDto.getLogo(), Set.of(store));
        brandRepository.save(brand);
        return BrandResponse.from(brand);
    }

    @Override
    public BrandResponse updateBrand(String id, BrandRequest brandRequestDto) {
        Brand dbBrand = findBrand(id);
        if (!dbBrand.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(brandRequestDto.getName()) && !brandRequestDto.getName().isEmpty()) {
            dbBrand.setName(brandRequestDto.getName());
        } else {
            throw new ItemCannotBeNullException("Name field");
        }
        if (Objects.nonNull(brandRequestDto.getDescription()) && !brandRequestDto.getDescription().isEmpty()) {
            dbBrand.setDescription(brandRequestDto.getDescription());
        } else {
            throw new ItemCannotBeNullException("Description field");
        }
        if (Objects.nonNull(brandRequestDto.getLogo())) {
            throw new ItemCannotBeNullException("Logo field");
        }
        return BrandResponse.from(dbBrand);
    }

    @Override
    public void deactivateBrand(String id) {
        Brand brand = findBrand(id);
        if (!brand.isActive()) throw new ItemNotFoundException("Brand");
        brand.setActive(false);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(String id) {
        Brand brand = findBrand(id);
        if (brand.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        brandRepository.delete(brand);
    }

    @Override
    public void activateBrand(String id) {
        Brand brand = findBrand(id);
        if (brand.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        brand.setActive(true);
        brandRepository.save(brand);
    }

    @Override
    public List<BrandResponse> getAllDeactivatedBrands() {
        return brandRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(BrandResponse::from)
                .collect(Collectors.toList());
    }


    private Brand findBrand(String id) {
        return brandRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Brand"));
    }
}
