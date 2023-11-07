package com.nextShop.store.model;

import com.nextShop.store.model.base.BaseEntityAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Store extends BaseEntityAudit {
    private String name;
    private byte score;

    @OneToMany(mappedBy = "store",  cascade = CascadeType.ALL)
    private List<Address> addresses;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="brand_id")
    private Brand brand;

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
