package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.model.Product;
import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.ReviewRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Review findById(Long id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        if (user != null){
            return reviewRepository.findByUserId(user);
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Review> findByProductId(Long id) {
        List<Review> reviews = reviewRepository.findByProductId(id);
        for(Review review : reviews){
            User user = new User();
            Product product = new Product();
            product.setId(review.getProduct().getId());
            user.setId(review.getUser().getId());
            user.setFirstName(review.getUser().getFirstName());
            user.setLastName(review.getUser().getLastName());
            review.setUser(user);
            review.setProduct(product);
        }
        return reviews;
    }
}
