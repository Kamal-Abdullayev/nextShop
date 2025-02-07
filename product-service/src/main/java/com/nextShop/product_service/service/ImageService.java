package com.nextShop.product_service.service;


import com.nextShop.product_service.dto.request.image.ImageUpdateRequestDto;
import com.nextShop.product_service.dto.request.image.ImageUploadRequestDto;
import com.nextShop.product_service.dto.response.ImageUploadResponseDto;
import com.nextShop.product_service.exception.InvalidParameterException;
import com.nextShop.product_service.exception.ResourceNotFoundException;
import com.nextShop.product_service.model.Image;
import com.nextShop.product_service.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;

    public List<ImageUploadResponseDto> getProductImages(Pageable pageable) {
        log.info("getProductImages called");
        return imageRepository.findAll(pageable).stream()
                .map(ImageUploadResponseDto::convert)
                .toList();
    }

    public ImageUploadResponseDto getProductImageById(String imageId) {
        log.info("Get product image by id: {}", imageId);
        return ImageUploadResponseDto.convert(getImageObjectById(imageId));
    }

    public Image getImageObjectById(String imageId) {
        if(imageId == null || imageId.isEmpty()) {
            log.error("Invalid image id provided or empty. Image id: {}", imageId);
            throw new InvalidParameterException("Invalid image id");
        }

        return imageRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException("Image not found")
        );
    }

    public String saveImage(ImageUploadRequestDto imageUploadRequestDto) {
        log.info("Save image called");
        return saveImageHandler(imageUploadRequestDto).getId();
    }

    public Image saveImageHandler(ImageUploadRequestDto imageUploadRequestDto) {
        if (imageUploadRequestDto.getImagePath() == null || imageUploadRequestDto.getImagePath().isEmpty()) {
            log.error("Invalid image path provided or empty. Image id: {}", imageUploadRequestDto.getImagePath());
            throw new InvalidParameterException("Invalid image path");
        }

        Image newImage = Image.builder()
                .imagePath(imageUploadRequestDto.getImagePath())
                .isMainImage(imageUploadRequestDto.isMainImage())
                .product(productService.getProductObjectById(imageUploadRequestDto.getProductId()))
                .build();

        log.info("Image saved: {}", newImage);
        return imageRepository.save(newImage);
    }


    public List<Image> saveImages(List<ImageUploadRequestDto> imageUploadRequestDtos) {
        if (imageUploadRequestDtos == null || imageUploadRequestDtos.isEmpty()) {
            log.error("ProductImageUploadRequestDto is null or empty");
            throw new InvalidParameterException("Invalid image upload request");
        }
        List<Image> newUploadedImageList = new ArrayList<>();
        imageUploadRequestDtos.forEach(imageUploadRequestDto -> {
            newUploadedImageList.add(saveImageHandler(imageUploadRequestDto));
        });
        log.info("Images saved: {}", newUploadedImageList);
        return newUploadedImageList;
    }

    public ImageUploadResponseDto updateImage(String imageId, ImageUpdateRequestDto productImageUploadRequestDto) {
        log.info("Updating image with id {}", imageId);
        if (productImageUploadRequestDto.getImagePath() == null || productImageUploadRequestDto.getImagePath().isEmpty()) {
            log.error("Invalid image path for update. Image path is empty. Image id: {}", imageId);
            throw new RuntimeException("Invalid image path");
        }
        Image dbImage = getImageObjectById(imageId);

        if (!productImageUploadRequestDto.getImagePath().equals(dbImage.getImagePath())) {
            log.info("Image path changed from {} to {}.", dbImage.getImagePath(), productImageUploadRequestDto.getImagePath());
            dbImage.setImagePath(productImageUploadRequestDto.getImagePath());
        }
        if (productImageUploadRequestDto.isMainImage() != dbImage.isMainImage()) {
            log.info("isMainImage changed from {} to {}.", dbImage.isMainImage(), productImageUploadRequestDto.isMainImage());
            dbImage.setMainImage(productImageUploadRequestDto.isMainImage());
        }
        if (!productImageUploadRequestDto.getProductId().equals(dbImage.getProduct().getId())) {
            log.info("Image product id changed from {} to {}.", dbImage.getProduct().getId(), productImageUploadRequestDto.getProductId());
            dbImage.setProduct(productService.getProductObjectById(productImageUploadRequestDto.getProductId()));
        }
        Image image = imageRepository.save(dbImage);
        log.info("Image updated successfully. Image id: {}", imageId);
        return ImageUploadResponseDto.convert(image);
    }

    public void deleteImage(String imageId) {
        Image dbImage = getImageObjectById(imageId);
        imageRepository.delete(dbImage);
        log.info("Image deleted successfully. Image id: {}", imageId);
    }
}
