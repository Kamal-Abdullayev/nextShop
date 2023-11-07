package com.nextShop.auth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String username;
    private String status;
    private Long roleId;
    private String email;
    private String password;
    private String phoneNumber;

    public Users(String name, String surname, String username, String status, Long roleId, String email, String password, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.status = status;
        this.roleId = roleId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
