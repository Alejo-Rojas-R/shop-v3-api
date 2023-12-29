package com.practice.shopv3api.dtos;

public class ProductDTO {
    private String name;
    private Float price;
    private Float discount;
    private String description;
    private Integer stock;
    private Long categoryId;
    private String imageUrl;

    public ProductDTO() {
    }

    public ProductDTO(String name, Float price, Float discount, String description, Integer stock, Long categoryId, String imageUrl) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    /*
    // Obtener instancia DTO apartir de la entidad
    public static ProductDTO fromEntityProduct(Product product) {
        return new ProductDTO();
    }

    // Obtener instancia de la entidad apartir de la instancia de un DTO

    public Product toEntity(){
        return new Product(this.name, this.price, this.discount, this.description, this.stock, this.categoryId, this.images);
    }
*/
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
