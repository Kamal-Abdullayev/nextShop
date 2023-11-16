package com.nextShop.store.model;

import com.nextShop.store.model.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "address")
public class Address extends BaseEntityAudit {
    private String country;
    private String city;
    private String address;
    private String zipCode;

    public Address(String country, String city, String address, String zipCode) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Address(String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String country, String city, String address, String zipCode) {
        super(createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Address(String id, String createdBy, String updatedBy, boolean isActive, Date createdAt, Date updatedAt, String country, String city, String address, String zipCode) {
        super(id, createdBy, updatedBy, isActive, createdAt, updatedAt);
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }
}
