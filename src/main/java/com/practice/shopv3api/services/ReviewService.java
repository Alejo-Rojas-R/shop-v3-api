package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.ReviewDTO;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.entities.Review;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.ProductRepository;
import com.practice.shopv3api.repositories.ReviewRepository;
import com.practice.shopv3api.repositories.UserRepository;
import com.practice.shopv3api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    JwtService jwtService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, JwtService jwtService) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public Review createReview(ReviewDTO dto, String token) {
        String userEmail = jwtService.extractUserName(token.substring(7));

        User user = this.userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ShopApiException("This user couldn't be found in the database"));

        Product product = this.productRepository.findById(dto.getIdProduct())
                .orElseThrow(() -> new ShopApiException("Couldn't find this product id in the database"));

        Review review = new Review(dto.getScore(), dto.getDescription(), dto.getDate(), product, user);
        return this.reviewRepository.save(review);
    }

    public List<Review> readReviewsByProductId(Long id) {
        return this.reviewRepository.findByProductId(id).stream().toList();
    }
}
