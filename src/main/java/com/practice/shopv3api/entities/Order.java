package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Float totalPrice;

    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    User user;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    Product product;

    public Order() {
    }

    public Order(Integer quantity, Float totalPrice, Date date, User user, Product product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

