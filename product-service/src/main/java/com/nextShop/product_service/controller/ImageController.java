package com.nextShop.product_service.controller;

import com.nextShop.product_service.dto.request.image.ImageUpdateRequestDto;
import com.nextShop.product_service.dto.request.image.ImageUploadRequestDto;
import com.nextShop.product_service.dto.response.ImageUploadResponseDto;
import com.nextShop.product_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageUploadResponseDto> getImageById(@PathVariable("id") String id) {
        return new ResponseEntity<>(imageService.getProductImageById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImageUploadResponseDto>> getAllImages(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", defaultValue = "2" ) int size) {

        return new ResponseEntity<>(imageService.getProductImages(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveImage(@RequestBody ImageUploadRequestDto imageUploadRequestDto) {
        return new ResponseEntity<>(imageService.saveImage(imageUploadRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageUploadResponseDto> updateImage(@PathVariable("id") String id, @RequestBody ImageUpdateRequestDto imageUpdateRequestDto) {
        return new ResponseEntity<>(imageService.updateImage(id, imageUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") String id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
