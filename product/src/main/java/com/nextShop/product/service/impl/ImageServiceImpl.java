package com.nextShop.product.service.impl;

import com.nextShop.product.dto.imageDto.ImageRequest;
import com.nextShop.product.dto.imageDto.ImageResponse;
import com.nextShop.product.entity.base.BaseEntityAudit;
import com.nextShop.product.entity.Image;
import com.nextShop.product.exceprion.CommonErrorCodesException;
import com.nextShop.product.exceprion.ItemCannotBeNullException;
import com.nextShop.product.exceprion.ItemNotFoundException;
import com.nextShop.product.repository.ImageRepository;
import com.nextShop.product.service.ImageService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;


    @Override
    public List<ImageResponse> getImageList() {
        return imageRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(ImageResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponse getImageById(String id) {
        Image image = findImage(id);
        if (!image.isActive()) throw new ItemNotFoundException("Image");
        return ImageResponse.from(image);
    }

    @Transactional
    @Override
    public String saveImage(ImageRequest imageRequest) {
        if (imageRequest.getImageContent() == null) throw new ItemCannotBeNullException("Image content");

        String imagePath = "storeName" + "/" + "userId_userName" + "/" + "userid" + "storeId" + System.currentTimeMillis() + ".png";
        Image image = new Image(
                imagePath,
                imageRequest.isMainImage(),
                imageRequest.getProduct()
        );
        imageRepository.save(image);
        saveImageToMinio(image.getImagePath(), imageRequest.getImageContent());
        return image.getId();
    }

    @Transactional
    @Override
    public List<String> saveAllImages(List<ImageRequest> imageResponseDtoList) {
        return imageResponseDtoList.stream().map(this::saveImage).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String updateImage(String id, ImageRequest imageRequest) {
        Image dbImage = findImage(id);
        if (!dbImage.isActive()) throw new ItemNotFoundException("Image");

        if (imageRequest.getImageContent() == null) throw new ItemCannotBeNullException("Image content");

        dbImage.setActive(false); // Deactivate image
        String imageId = saveImage(imageRequest);
        return imageId;
    }

    @Override
    public void deactivateImage(String id) {
        Image image = findImage(id);
        if (!image.isActive()) throw new ItemNotFoundException("Image");
        image.setActive(false);
        imageRepository.save(image);
    }

    @Override
    public void deleteImage(String id) {
        Image image = findImage(id);
        if (image.isActive()) throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        imageRepository.delete(image);
    }

    @Override
    public void activateImage(String id) {
        Image image = findImage(id);
        if (image.isActive()) throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        image.setActive(true);
        imageRepository.save(image);
    }

    @Override
    public List<ImageResponse> getAllDeactivatedImages() {
        return imageRepository.findAll()
                .stream()
                .filter(img -> !img.isActive())
                .map(ImageResponse::from)
                .collect(Collectors.toList());
    }

    private Image findImage(String id) {
        return imageRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Image"));
    }
    private void saveImageToMinio(String imagePath, byte[] imageContent) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(imagePath)
                    .stream(new ByteArrayInputStream(imageContent), imageContent.length, -1)
                    .build()
            );
        } catch (ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException |
                 ServerException | XmlParserException e) {
            throw new RuntimeException(CommonErrorCodesException.FILE_CANNOT_UPLOAD_TO_SERVICE_STORAGE);
        }
    }


}
