package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price;
    private Float discount;
    @OneToMany(mappedBy = "product")
    List<Order> order;

    @OneToMany(mappedBy = "product")
    List<Image> image;

    public Product() {
    }

    public Product(String name, Float price, Float discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}
