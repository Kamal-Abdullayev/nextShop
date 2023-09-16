package com.nextShop.product.repository;

import com.nextShop.product.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColorRepository extends JpaRepository<Color, String> {

}
