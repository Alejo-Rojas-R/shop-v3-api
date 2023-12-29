package com.practice.shopv3api.dtos;

import java.util.Date;

public class ReviewDTO {
    private Integer score;
    private String description;
    private Date date;
    private Long idProduct;
    private Long idUser;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer score, String description, Date date, Long idProduct, Long idUser) {
        this.score = score;
        this.description = description;
        this.date = date;
        this.idProduct = idProduct;
        this.idUser = idUser;
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

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
