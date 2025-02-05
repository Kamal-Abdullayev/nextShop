package com.nextShop.product_service.model;

import com.nextShop.product_service.model.enums.ProductColorEnum;
import com.nextShop.product_service.model.enums.ProductSizeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private double price;
    private double discountPrice;
    private String description;
    private int quantity;

    @ElementCollection(targetClass = ProductColorEnum.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "product_colors", nullable = false)
    private List<ProductColorEnum> colors;

    @ElementCollection(targetClass = ProductSizeEnum.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "product_sizes", nullable = false)
    private List<ProductSizeEnum> sizes;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
}
