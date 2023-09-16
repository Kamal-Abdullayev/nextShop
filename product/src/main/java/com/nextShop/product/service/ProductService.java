package com.nextShop.product.service;

import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.dto.productDto.ProductSaveRequest;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProductList();
    ProductResponse getProductById(String id);
    void saveProduct(ProductSaveRequest productSaveRequest);
}
