package com.nextShop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "size")
public class Size extends BaseEntityAudit {
    private String size;

    @JsonIgnore
    @ManyToOne
    private Product product;

}
