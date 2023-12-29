package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "\"order\"")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Float totalPrice;

    private Date date;

    @ManyToOne(optional = false)
    UserEntity userEntity;

    @ManyToOne(optional = false)
    ProductEntity product;

    public OrderEntity() {
    }

    public OrderEntity(Integer quantity, Float totalPrice, Date date, UserEntity userEntity, ProductEntity product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.userEntity = userEntity;
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

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}

