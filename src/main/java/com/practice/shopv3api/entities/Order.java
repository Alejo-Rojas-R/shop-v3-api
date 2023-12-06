package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Float totalPrice;

    private Date date;

    @ManyToOne(optional = false)
    User user;

    @ManyToOne(optional = false)
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
}

