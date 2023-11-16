package com.nextShop.product.entity;


import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image extends BaseEntityAudit {
    private String imagePath;
    @Column(name = "is_main")
    private boolean isMainImage;

}
