package com.nextShop.product.entity;

import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Size> sizeList;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Image> imageList;
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Color> colorList;


    public Product(String name, double price, double discountPrice, String description, List<Size> sizeList, List<Image> imageList, List<Category> categoryList, List<Color> colorList) {
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.sizeList = sizeList;
        this.imageList = imageList;
        this.categoryList = categoryList;
        this.colorList = colorList;
    }

    public Product(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, double price, double discountPrice, String description, List<Size> sizeList, List<Image> imageList, List<Category> categoryList, List<Color> colorList) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.sizeList = sizeList;
        this.imageList = imageList;
        this.categoryList = categoryList;
        this.colorList = colorList;
    }

    public Product(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, double price, double discountPrice, String description, List<Size> sizeList, List<Image> imageList, List<Category> categoryList, List<Color> colorList) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.sizeList = sizeList;
        this.imageList = imageList;
        this.categoryList = categoryList;
        this.colorList = colorList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", sizeList=" + sizeList +
                ", imageList=" + imageList +
                ", categoryList=" + categoryList +
                ", colorList=" + colorList +
                '}';
    }
}
