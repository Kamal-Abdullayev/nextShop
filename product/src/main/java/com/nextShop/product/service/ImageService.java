package com.nextShop.product.service;

import com.nextShop.product.dto.imageDto.ImageRequest;
import com.nextShop.product.dto.imageDto.ImageResponse;
import com.nextShop.product.entity.Image;

import java.util.List;

public interface ImageService {
    List<ImageResponse> getImageList();
    ImageResponse getImageById(String id);
    Image getImageObject(String id);
    String saveImage(ImageRequest imageRequest);
    List<String> saveAllImages(List<ImageRequest> imageResponseDtoList);
    String updateImage(String id, ImageRequest imageRequest);
    void deactivateImage(String id);
    void deleteImage(String id);
    void activateImage(String id);
    List<ImageResponse> getAllDeactivatedImages();
}
