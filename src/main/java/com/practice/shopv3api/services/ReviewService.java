package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ReviewDTO;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.entities.Review;
import com.practice.shopv3api.entities.User;
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

    public Review createReview(ReviewDTO dto) {
        Product product = this.productRepository.findById(dto.getIdProduct()).orElseThrow(() -> new ShopApiException("Couldn't find this product id in the database"));
        User user = this.userRepository.findById(dto.getIdProduct()).orElseThrow(() -> new ShopApiException("Couldn't find this user id in the database"));

        Review review = new Review(dto.getScore(), dto.getDescription(), dto.getDate(), product, user);
        return this.reviewRepository.save(review);
    }
}
