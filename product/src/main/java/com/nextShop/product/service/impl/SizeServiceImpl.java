package com.nextShop.product.service.impl;

import com.nextShop.product.dto.sizeDto.SizeDtoRequest;
import com.nextShop.product.dto.sizeDto.SizeDtoResponse;
import com.nextShop.product.entity.base.BaseEntityAudit;
import com.nextShop.product.entity.Size;
import com.nextShop.product.exceprion.CommonErrorCodesException;
import com.nextShop.product.exceprion.ItemCannotBeNullException;
import com.nextShop.product.exceprion.ItemNotFoundException;
import com.nextShop.product.repository.SizeRepository;
import com.nextShop.product.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public List<SizeDtoResponse> getSizeList() {
        return sizeRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(SizeDtoResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public SizeDtoResponse getSizeById(String id) {
        Size size = findSize(id);
        if (!size.isActive()) {
            throw new ItemNotFoundException("Size");
        }
        return SizeDtoResponse.from(size);
    }

    @Override
    public Size findSizeObject(String id) {
        Size size = findSize(id);
        if (!size.isActive()) {
            throw new ItemNotFoundException("Size");
        }
        return size;
    }

    @Override
    public String saveSize(SizeDtoRequest sizeDtoRequest) {
        if (sizeDtoRequest.getSize() == null) {
            throw new ItemCannotBeNullException("Size");
        }
        Size size = new Size(sizeDtoRequest.getSize());
        sizeRepository.save(size);
        return size.getId();
    }

    @Override
    public SizeDtoResponse updateSize(String id, SizeDtoRequest sizeDtoRequest) {
        Size dbSize = findSize(id);
        if (!dbSize.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(sizeDtoRequest.getSize()) && !sizeDtoRequest.getSize().isEmpty()) {
            dbSize.setSize(sizeDtoRequest.getSize());
        } else {
            throw new ItemNotFoundException("Size field");
        }
        sizeRepository.save(dbSize);
        return SizeDtoResponse.from(dbSize);
    }

    @Override
    public void deactivateSize(String id) {
        Size size = findSize(id);
        if (!size.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_ALREADY_DELETED);
        }
        size.setActive(false);
        sizeRepository.save(size);
    }

    @Override
    public void deleteSize(String id) {
        Size size = findSize(id);
        if (size.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        sizeRepository.delete(size);
    }

    @Override
    public void activateSize(String id) {
        Size size = findSize(id);
        if (size.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        size.setActive(true);
        sizeRepository.save(size);
    }

    @Override
    public List<SizeDtoResponse> getAllDeactivatedSizes() {
        return sizeRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(SizeDtoResponse::from)
                .collect(Collectors.toList());
    }

    private Size findSize(String id) {
        return sizeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Size"));
    }
}
