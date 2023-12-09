package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.Category;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.CategoryRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createProduct(ProductDTO dto) {
        Category category = this.categoryRepository.findById(dto.getCategoryId()).orElseThrow(
                () -> new ShopApiException("This product couldn't be found in the database"));
        Product newProduct = new Product(dto.getName(), dto.getPrice(), dto.getDiscount(), dto.getDescription(), dto.getStock(), category);
        this.productRepository.save(newProduct);
    }

    public List<Product> readProducts() {
        return StreamSupport.stream(this.productRepository.findAll().spliterator(), false).toList();
    }

    public Product readProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new ShopApiException("This product couldn't be found in the database"));
    }

    public Product updateProduct(Long productId, ProductDTO productBody) {
        Product product = readProductById(productId);

        product.setName(productBody.getName());
        product.setDiscount(productBody.getDiscount());
        product.setPrice(productBody.getPrice());

        return this.productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        readProductById(productId);
        this.productRepository.deleteById(productId);
    }
}
