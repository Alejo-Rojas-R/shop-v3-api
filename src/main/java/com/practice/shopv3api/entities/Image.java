package com.practice.shopv3api.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;

    @ManyToOne(optional = false)
    Product product;

    public Image() {
    }

    public Image(String url, String description, Product product) {
        this.url = url;
        this.description = description;
        this.product = product;
    }
}
