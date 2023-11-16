package com.nextShop.store.model;

import com.nextShop.store.model.base.BaseEntityAudit;
import jakarta.persistence.*;
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
    @Lob
    private byte[] logo;
    private byte score;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Store> stores;

    public Brand(String name, String description, byte[] logo, Set<Store> stores) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.stores = stores;
    }

    public Brand(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, String description, byte[] logo, Set<Store> stores) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.stores = stores;
    }

    public Brand(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, String description, byte[] logo, Set<Store> stores) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.stores = stores;
    }
}
