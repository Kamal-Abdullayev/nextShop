package com.nextShop.product.service;

import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.dto.productDto.ProductSaveRequest;
import com.nextShop.product.dto.productDto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProductList();
    ProductResponse getProductById(String id);
    void saveProduct(ProductSaveRequest productSaveRequest);
    ProductResponse updateProduct(String id, ProductUpdateRequest productUpdateRequest);
    void deactivateProduct(String id);
    void deleteProduct(String id);
    void activateProduct(String id);
    List<ProductResponse> getAllDeactivatedProducts();
}
