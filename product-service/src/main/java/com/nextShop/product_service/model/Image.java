package com.nextShop.product_service.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Image  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String imagePath;
    private boolean isMainImage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
