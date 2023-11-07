package com.nextShop.store.model;

import com.nextShop.store.model.base.BaseEntityAudit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Brand extends BaseEntityAudit {
    private String name;
    private String description;
    private byte[] logo;
    private byte score;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Store> stores;

    public Brand(String name, String description, @NotNull byte[] logo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public Brand(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, String description, @NotNull byte[] logo) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public Brand(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, String description, @NotNull byte[] logo) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.logo = logo;
    }
}
