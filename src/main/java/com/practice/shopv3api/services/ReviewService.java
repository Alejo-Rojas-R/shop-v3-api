package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ReviewDTO;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.entities.ReviewEntity;
import com.practice.shopv3api.entities.UserEntity;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.ProductRepository;
import com.practice.shopv3api.repositories.ReviewRepository;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ReviewEntity createReview(ReviewDTO dto) {
        ProductEntity product = this.productRepository.findById(dto.getIdProduct()).orElseThrow(() -> new ShopApiException("Couldn't find this product id in the database"));
        UserEntity userEntity = this.userRepository.findById(dto.getIdProduct()).orElseThrow(() -> new ShopApiException("Couldn't find this user id in the database"));

        ReviewEntity reviewEntity = new ReviewEntity(dto.getScore(), dto.getDescription(), dto.getDate(), product, userEntity);
        return this.reviewRepository.save(reviewEntity);
    }
}
