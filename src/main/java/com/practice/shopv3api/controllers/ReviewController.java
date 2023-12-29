package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ReviewDTO;
import com.practice.shopv3api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public void createReview(@RequestBody ReviewDTO dto) {
        this.service.createReview(dto);
    }
}
