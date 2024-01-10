package com.practice.shopv3api.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price;
    private Float discount;
    @Column(columnDefinition = "TEXT")
    private String description;
    @NonNull
    private Integer stock;
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    List<Order> order;
    @OneToMany(mappedBy = "product")
    List<Image> images;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    Category category;
    @OneToMany(mappedBy = "product")
    List<Review> review;

    public Product() {
    }

    public Product(String name, Float price, Float discount, String description, Integer stock, String imageUrl, Category category) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Review> getReview() {
        return review;
    }
}
