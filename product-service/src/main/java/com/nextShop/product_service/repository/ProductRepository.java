package com.nextShop.product_service.repository;


import com.nextShop.product_service.dto.response.ProductResponseDto;
import com.nextShop.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product getProductById(String id);
}
