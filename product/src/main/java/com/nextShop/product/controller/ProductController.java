package com.nextShop.product.controller;

import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.dto.productDto.ProductSaveRequest;
import com.nextShop.product.dto.productDto.ProductUpdateRequest;
import com.nextShop.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveProduct(@Valid @RequestBody ProductSaveRequest productSaveRequest) {
        productService.saveProduct(productSaveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProducts(@PathVariable("id") String id, @RequestBody ProductUpdateRequest productUpdateRequest) {
        productService.updateProduct(id, productUpdateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateProduct(@PathVariable("id") String id) {
        productService.activateProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateProduct(@PathVariable("id") String id) {
        productService.deactivateProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<ProductResponse>> getAllDeactivatedProducts() {
        return new ResponseEntity<>(productService.getAllDeactivatedProducts(), HttpStatus.OK);
    }


}

