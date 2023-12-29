package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ImageDTO;
import com.practice.shopv3api.entities.ImageEntity;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.ImageRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    ImageRepository imageRepository;
    ProductRepository productRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public List<ImageEntity> readImagesByProductId(Long productId) {
        return this.imageRepository.findByProductId(productId).stream().toList();
    }

    public void create(ImageDTO dto) {
        ProductEntity product = this.productRepository.findById(dto.getProductId()).orElseThrow(()->new ShopApiException("This product doesn't exist in the database"));
        ImageEntity newImageEntity = new ImageEntity(dto.getUrl(), dto.getDescription(), product);
        this.imageRepository.save(newImageEntity);
    }

    public void delete(Long id) {
        this.imageRepository.findById(id).orElseThrow(()->new ShopApiException("This image id doesn't exist in the database"));
        this.imageRepository.deleteById(id);
    }
}
