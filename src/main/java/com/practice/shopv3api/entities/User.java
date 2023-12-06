package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Integer phone;
    private String address;
    private Boolean isAdmin;

    @OneToMany(mappedBy = "user")
    List<Order> order;

    public User() {
    }

    public User(String name, String lastName, String email, String password, Integer phone, String address, Boolean isAdmin) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
    }
}
