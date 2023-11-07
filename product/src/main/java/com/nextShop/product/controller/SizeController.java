package com.nextShop.product.controller;

import com.nextShop.product.dto.sizeDto.SizeDtoRequest;
import com.nextShop.product.dto.sizeDto.SizeDtoResponse;
import com.nextShop.product.service.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sizes")
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("/all")
    public ResponseEntity<List<SizeDtoResponse>> getSizeList() {
        return new ResponseEntity<>(sizeService.getSizeList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeDtoResponse> getSizeDtoById(@PathVariable("id") String id) {
        return new ResponseEntity<>(sizeService.getSizeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveSize(@Valid @RequestBody SizeDtoRequest sizeDtoRequest) {
        return new ResponseEntity<>(sizeService.saveSize(sizeDtoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeDtoResponse> updateSize(@PathVariable("id") String id, @Valid @RequestBody SizeDtoRequest sizeDtoRequest) {
        return new ResponseEntity<>(sizeService.updateSize(id, sizeDtoRequest), HttpStatus.OK);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateSize(@PathVariable("id") String id) {
        sizeService.deactivateSize(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSize(@PathVariable("id") String id) {
        sizeService.deleteSize(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateSize(@PathVariable("id") String id) {
        sizeService.activateSize(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<SizeDtoResponse>> getAllDeactivatedSizes() {
        return new ResponseEntity<>(sizeService.getAllDeactivatedSizes(), HttpStatus.OK);
    }
}
