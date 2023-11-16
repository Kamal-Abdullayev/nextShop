package com.nextShop.product.entity;

import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Color extends BaseEntityAudit {
    private String color;

}
