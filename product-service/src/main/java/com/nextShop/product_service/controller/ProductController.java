package com.nextShop.product_service.controller;

import com.nextShop.product_service.dto.request.product.ProductCreateRequestDto;
import com.nextShop.product_service.dto.request.product.ProductPurchaseRequestDto;
import com.nextShop.product_service.dto.request.product.ProductUpdateRequestDto;
import com.nextShop.product_service.dto.response.ProductPurchaseResponseDto;
import com.nextShop.product_service.dto.response.ProductResponseDto;
import com.nextShop.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "2" ) int size) {
        return new ResponseEntity<>(productService.getAllProducts(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProducts(@RequestBody List<ProductPurchaseRequestDto> purchaseRequestDtoList) {
        return new ResponseEntity<>(productService.productPurchase(purchaseRequestDtoList), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return new ResponseEntity<>(productService.saveProduct(productCreateRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") String id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

