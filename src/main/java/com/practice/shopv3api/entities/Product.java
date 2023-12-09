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
    private String description;
    private Integer stock;
    @OneToMany(mappedBy = "product")
    List<Order> order;
    @OneToMany(mappedBy = "product")
    List<Image> image;
    @ManyToOne(optional = false)
    Category category;

    public Product() {
    }

    public Product(String name, Float price, Float discount, String description, Integer stock, Category category) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
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
}
