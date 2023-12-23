package com.nextShop.store.model;

import com.nextShop.store.model.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "store")
public class Store extends BaseEntityAudit {
    private String name;
    private byte score;
    @ElementCollection
    List<String> productId;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Address> addresses;

    public Store(String name, List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public Store(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, List<Address> addresses) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.addresses = addresses;
    }

    public Store(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String name, List<Address> addresses) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.name = name;
        this.addresses = addresses;
    }
}
