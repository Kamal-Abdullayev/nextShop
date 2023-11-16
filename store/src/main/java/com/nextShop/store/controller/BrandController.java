package com.nextShop.store.controller;

import com.nextShop.store.dto.brand.request.BrandRequest;
import com.nextShop.store.dto.brand.response.BrandResponse;
import com.nextShop.store.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/all")
    public ResponseEntity<List<BrandResponse>> getBrandList() {
        return new ResponseEntity<>(brandService.getBrandList(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id") String id) {
        return new ResponseEntity<>(brandService.getBrandById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<BrandResponse> saveBrand(@RequestBody BrandRequest brandRequest) {
        return new ResponseEntity<>(brandService.saveBrand(brandRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable("id") String id, @RequestBody BrandRequest brandRequest) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateBrand(@PathVariable("id") String id) {
        brandService.deactivateBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") String id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateBrand(@PathVariable("id") String id) {
        brandService.activateBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<BrandResponse>> getAllDeactivatedBrands() {
        return new ResponseEntity<>(brandService.getAllDeactivatedBrands(), HttpStatus.OK);
    }
}
