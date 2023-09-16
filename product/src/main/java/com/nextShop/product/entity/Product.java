package com.nextShop.product.entity;

import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntityAudit {
    private String name;
    private double price;
    @Column(name = "discount_price")
    private double discountPrice;
    private String description;
    private byte rate;

    // Relations
    @OneToMany
    private List<Size> sizeList;
    @OneToMany
    private List<Image> imageList;
    @ManyToMany
    private List<Category> categoryList;
    @OneToMany
    private List<Color> colorList;

    // Store

}
