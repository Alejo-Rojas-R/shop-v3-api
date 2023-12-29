package com.practice.shopv3api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;

    @ManyToOne(optional = false)
    ProductEntity product;

    public ImageEntity() {
    }

    public ImageEntity(String url, String description, ProductEntity product) {
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

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
