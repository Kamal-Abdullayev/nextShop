package com.nextShop.product.service.impl;

import com.nextShop.product.dto.colorDto.ColorRequest;
import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.entity.base.BaseEntityAudit;
import com.nextShop.product.entity.Color;
import com.nextShop.product.exceprion.CommonErrorCodesException;
import com.nextShop.product.exceprion.ItemCannotBeNullException;
import com.nextShop.product.exceprion.ItemNotFoundException;
import com.nextShop.product.repository.ColorRepository;
import com.nextShop.product.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getColorList() {
        return colorRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(ColorResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ColorResponse getColorById(String id) {
        Color color = findColor(id);
        if (!color.isActive()) {
            throw new ItemNotFoundException("Color");
        }
        return ColorResponse.from(color);
    }

    @Override
    public Color getColorObject(String id) {
        Color color = findColor(id);
        if (!color.isActive()) {
            throw new ItemNotFoundException("Color");
        }
        return color;
    }

    @Override
    public String saveColor(ColorRequest colorDtoRequest) {
        if (colorDtoRequest.getColor() == null) {
            throw new ItemCannotBeNullException("Color");
        }
        Color color = new Color(colorDtoRequest.getColor());
        colorRepository.save(color);
        return color.getId();
    }

    @Override
    public ColorResponse updateColor(String id, ColorRequest colorDtoRequest) {
        Color dbColor = findColor(id);
        if (!dbColor.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(colorDtoRequest.getColor()) && !colorDtoRequest.getColor().isEmpty()) {
            dbColor.setColor(colorDtoRequest.getColor());
        } else {
            throw new ItemCannotBeNullException("Color field");
        }
        colorRepository.save(dbColor);
        return ColorResponse.from(dbColor);
    }

    @Override
    public void deactivateColor(String id) {
        Color color = findColor(id);
        if (!color.isActive()) throw new ItemNotFoundException("Color");
        color.setActive(false);
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(String id) {
        Color color = findColor(id);
        if (color.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        colorRepository.delete(color);
    }

    @Override
    public void activateColor(String id) {
        Color color = findColor(id);
        if (color.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        color.setActive(true);
        colorRepository.save(color);
    }

    @Override
    public List<ColorResponse> getAllDeactivatedColors() {
        return colorRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(ColorResponse::from)
                .collect(Collectors.toList());
    }


    private Color findColor(String id) {
        return colorRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Color"));
    }
}
