package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;
    private Date date;
    @ManyToOne(optional = false)
    ProductEntity product;
    @ManyToOne(optional = false)
    UserEntity userEntity;

    public ReviewEntity() {
    }

    public ReviewEntity(Integer score, String description, Date date, ProductEntity product, UserEntity userEntity) {
        this.score = score;
        this.description = description;
        this.date = date;
        this.product = product;
        this.userEntity = userEntity;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
