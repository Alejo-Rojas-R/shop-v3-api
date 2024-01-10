package com.practice.shopv3api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;
    private Date date;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    Product product;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    User user;

    public Review() {
    }

    public Review(Integer score, String description, Date date, Product product, User user) {
        this.score = score;
        this.description = description;
        this.date = date;
        this.product = product;
        this.user = user;
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

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
