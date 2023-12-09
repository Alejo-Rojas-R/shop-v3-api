package com.practice.shopv3api.dtos;

import java.util.Date;

public class OrderDTO {
    private Integer quantity;
    private Float totalPrice;
    private Date date;
    private Long idUser;
    private Long idProduct;
    public OrderDTO() {
    }
    public OrderDTO(Integer quantity, Float totalPrice, Date date, Long idUser, Long idProduct) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.idUser = idUser;
        this.idProduct = idProduct;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}
