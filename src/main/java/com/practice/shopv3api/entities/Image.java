package com.practice.shopv3api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    Product product;

    public Image() {
    }

    public Image(String url, String description, Product product) {
        this.url = url;
        this.description = description;
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
