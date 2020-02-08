package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable(value = "id") Long reviewId){
        return reviewService.findById(reviewId);
    }

    @PostMapping("/reviews")
    public Review createReview(HttpServletRequest request, @Valid @RequestBody Review review) throws IOException {
        review.setSentiment(reviewService.getSentimentalAnalysis(review.getContent()));
        return reviewService.create(review);
    }
}
