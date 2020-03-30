package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.constants.LogTypes;
import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.model.UserActivity;
import com.sliit.research.blockchainbasedapplication.repository.UserActivityRepository;
import com.sliit.research.blockchainbasedapplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserActivityRepository userActivityRepository;

    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable(value = "id") Long reviewId){
        return reviewService.findById(reviewId);
    }

    @PostMapping("/reviews")
    public Review createReview(HttpServletRequest request, @Valid @RequestBody Review review) throws IOException {
        review.setSentiment(reviewService.getSentimentalAnalysis(review.getContent()));
        review = reviewService.create(review);
        UserActivity userActivity = new UserActivity(
                LogTypes.REVIEW,
                new Date(),
                null,
                null,
                review.getId()
        );
        userActivityRepository.save(userActivity);
        return review;
    }
}
