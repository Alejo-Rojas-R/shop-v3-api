package com.practice.shopv3api.dtos;

public class ProductDTO {
    private String name;
    private Float price;
    private Float discount;
    private String description;
    private Integer stock;
    private Long categoryId;

    public ProductDTO() {
    }
    public ProductDTO(String name, Float price, Float discount, String description, Integer stock, Long categoryId) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.stock = stock;
        this.categoryId = categoryId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
