package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.Category;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.CategoryRepository;
import com.practice.shopv3api.repositories.ImageRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Category category = this.categoryRepository.findById(dto.getCategoryId()).orElseThrow(
                () -> new ShopApiException("This product couldn't be found in the database"));
        Product newProduct = new Product(dto.getName(), dto.getPrice(), dto.getDiscount(), dto.getDescription(), dto.getStock(), dto.getImageUrl(), category);
        this.productRepository.save(newProduct);
    }

    public List<Product> readProducts() {
        List<Product> products = StreamSupport.stream(this.productRepository.findAll().spliterator(), false).toList();

        return products;
    }

    public List<Product> readProductsByCategory(Integer categoryId, Pageable pageable) {
        List<Product> products = StreamSupport.stream(this.productRepository.findByCategory_Id(categoryId, pageable).spliterator(), false).toList();

        return products;
    }

    public Product readProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new ShopApiException("This product was not found in the database"));
    }

    public Product updateProduct(Long productId, ProductDTO productBody) {
        Product product = readProductById(productId);

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
            Category category = this.categoryRepository.findById(productBody.getCategoryId()).orElseThrow(() -> new ShopApiException("This category was not found in the database"));

            product.setCategory(category);
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

    public List<Product> readProductsBySearch(String query, Pageable pageable) {
        List<Product> products = StreamSupport.stream(this.productRepository.findByNameLikeOrDescriptionLike(query, query, pageable).spliterator(), false).toList();

        return products;
    }
}
