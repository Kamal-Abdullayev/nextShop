package com.nextShop.product_service.repository;

import com.nextShop.product_service.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
