package com.practice.shopv3api.dtos;

public class ImageDTO {
    private String url;
    private String description;
    private Long idProduct;

    public ImageDTO() {
    }

    public ImageDTO(String url, String description, Long idProduct) {
        this.url = url;
        this.description = description;
        this.idProduct = idProduct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}
