package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.CategoryEntity;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.CategoryRepository;
import com.practice.shopv3api.repositories.ImageRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ImageRepository imageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public void createProduct(ProductDTO dto) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(dto.getCategoryId()).orElseThrow(
                () -> new ShopApiException("This product couldn't be found in the database"));
        ProductEntity newProduct = new ProductEntity(dto.getName(), dto.getPrice(), dto.getDiscount(), dto.getDescription(), dto.getStock(), dto.getImageUrl(), categoryEntity);
        this.productRepository.save(newProduct);
    }

    public List<ProductEntity> readProducts() {
        //List<Image> images = imageRepository.findByProductId();
        List<ProductEntity> products = StreamSupport.stream(this.productRepository.findAll().spliterator(), false).toList();
        //List<ProductDTO> productsDTO =  products.stream().map(product -> ProductDTO.fromEntityProduct(product)).toList();

        return products;
    }

    public ProductEntity readProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new ShopApiException("This product was not found in the database"));
    }

    public ProductEntity updateProduct(Long productId, ProductDTO productBody) {
        ProductEntity product = readProductById(productId);

        if(productBody.getName() != null) {
            product.setName(productBody.getName());
        }
        if(productBody.getDiscount() != null) {
            product.setDiscount(productBody.getDiscount());
        }
        if(productBody.getPrice() != null) {
            product.setPrice(productBody.getPrice());
        }
        if(productBody.getCategoryId() != null) {
            CategoryEntity categoryEntity = this.categoryRepository.findById(productBody.getCategoryId()).orElseThrow(() -> new ShopApiException("This category was not found in the database"));

            product.setCategory(categoryEntity);
        }
        if(productBody.getDescription() != null) {
            product.setDescription(productBody.getDescription());
        }
        if(productBody.getStock() != null) {
            product.setStock(productBody.getStock());
        }

        return this.productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        readProductById(productId);
        this.productRepository.deleteById(productId);
    }
}
