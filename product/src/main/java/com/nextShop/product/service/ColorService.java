package com.nextShop.product.service;

import com.nextShop.product.dto.colorDto.ColorRequest;
import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.entity.Color;

import java.util.List;

public interface ColorService {
    List<ColorResponse> getColorList();
    ColorResponse getColorById(String id);
    Color getColorObject(String id);
    String saveColor(ColorRequest colorDtoRequest);
    ColorResponse updateColor(String id, ColorRequest colorDtoRequest);
    void deactivateColor(String id);
    void deleteColor(String id);
    void activateColor(String id);
    List<ColorResponse> getAllDeactivatedColors();
}
