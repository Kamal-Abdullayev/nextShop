package com.nextShop.product_service.service;


import com.nextShop.product_service.dto.response.ProductResponseDto;
import com.nextShop.product_service.model.Product;
import com.nextShop.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::convert)
                .toList();
    }

    public ProductResponseDto getProductById(String productId) {
        if(productId == null || productId.isEmpty()) {
            throw new RuntimeException("Invalid product id"); // TODO: Add custom exception
        }
        return ProductResponseDto.convert(
                productRepository.findById(productId)
                        .orElseThrow()); // TODO: Add custom exception
    }

    public ProductResponseDto saveProduct(Product product) {
        // TODO: Validation
        return ProductResponseDto.convert(productRepository.save(product));
    }

}
