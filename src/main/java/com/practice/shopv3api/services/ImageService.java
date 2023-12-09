package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ImageDTO;
import com.practice.shopv3api.entities.Image;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.ImageRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    ImageRepository imageRepository;
    ProductRepository productRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public void crear(ImageDTO dto) {
        Product product = this.productRepository.findById(dto.getIdProduct()).orElseThrow(()->new ShopApiException("This product doesn't exist in the database"));
        Image newImage = new Image(dto.getUrl(), dto.getDescription(), product);
        this.imageRepository.save(newImage);
    }

    public void delete(Long id) {
        this.imageRepository.findById(id).orElseThrow(()->new ShopApiException("This image id doesn't exist in the database"));
        this.imageRepository.deleteById(id);
    }
}
