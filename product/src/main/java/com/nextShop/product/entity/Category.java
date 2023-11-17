package com.nextShop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseEntityAudit {
    private String categoryName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Category> categoryChildrensList = new LinkedList<>();

    @JsonIgnore
    @ManyToMany
    private List<Product> productList;

}
