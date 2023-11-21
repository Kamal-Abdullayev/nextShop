package com.nextShop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextShop.product.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @ManyToMany(mappedBy = "categoryList")
    private List<Product> productList;

    public Category(String categoryName, Category parentCategory, List<Category> categoryChildrensList) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.categoryChildrensList = categoryChildrensList;
    }

    public Category(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String categoryName, Category parentCategory, List<Category> categoryChildrensList) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.categoryChildrensList = categoryChildrensList;
    }

    public Category(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String categoryName, Category parentCategory, List<Category> categoryChildrensList) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.categoryChildrensList = categoryChildrensList;
    }
}
