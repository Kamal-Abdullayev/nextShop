package com.nextShop.product.controller;

import com.nextShop.product.dto.imageDto.ImageRequest;
import com.nextShop.product.dto.imageDto.ImageResponse;
import com.nextShop.product.service.ImageService;
import com.nextShop.product.service.impl.ImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/all")
    public ResponseEntity<List<ImageResponse>> getImageList() {
        return new ResponseEntity<>(imageService.getImageList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getImageById(@PathVariable("id") String id) {
        return new ResponseEntity<>(imageService.getImageById(id) ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveImage(@Valid @RequestBody ImageRequest imageRequest) {
        return new ResponseEntity<>(imageService.saveImage(imageRequest) ,HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<String>> saveAllImages(@Valid @RequestBody List<ImageRequest> imageRequestList) {
        return new ResponseEntity<>(imageService.saveAllImages(imageRequestList), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateImages() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateImage(@PathVariable("id") String id) {
        imageService.activateImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateImage(@PathVariable("id") String id) {
        imageService.deactivateImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable("id") String id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<ImageResponse>> getAllDeactivatedImages() {
        return new ResponseEntity<>(imageService.getAllDeactivatedImages(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@PathVariable("id") String id, @Valid @RequestBody ImageRequest imageRequest) {
        return new ResponseEntity<>(imageService.updateImage(id, imageRequest), HttpStatus.OK);
    }


}
