package com.practice.shopv3api.dtos;

public class ImageDTO {
    private String url;
    private String description;
    private Long productId;

    public ImageDTO() {
    }

    public ImageDTO(String url, String description, Long productId) {
        this.url = url;
        this.description = description;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long idProduct) {
        this.productId = idProduct;
    }
}
