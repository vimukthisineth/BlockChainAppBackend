package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.model.Review;

import java.util.List;

public interface ReviewService {
    Review findById(Long id);
    List<Review> getAll();
    Review create(Review review);
    List<Review> findByUserId(Long userId);
}
