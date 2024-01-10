package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ReviewDTO;
import com.practice.shopv3api.entities.Review;
import com.practice.shopv3api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public List<Review> readReviewsByProductId(@PathVariable("id") Long id) {
        return this.service.readReviewsByProductId(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(this.service.createReview(dto, token));
    }
}
