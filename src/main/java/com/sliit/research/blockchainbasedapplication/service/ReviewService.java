package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.model.Review;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ReviewService {
    Review findById(Long id);
    List<Review> getAll();
    Review create(Review review);
    List<Review> findByUserId(Long userId);
    List<Review> findByProductId(Long id);
    String getSentimentalAnalysis(String review) throws IOException;
    String getAspectAnalysis(String review) throws IOException;
}
