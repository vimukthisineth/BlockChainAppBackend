package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.exception.ResourceNotFoundException;
import com.sliit.research.blockchainbasedapplication.model.Farmer;
import com.sliit.research.blockchainbasedapplication.model.Product;
import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.ProductRepository;
import com.sliit.research.blockchainbasedapplication.repository.ReviewRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/products")
    public Product createProduct(HttpServletRequest request, @Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @GetMapping("/products/reviewsByProduct/{id}")
    public List<Review> getReviewsByProductId(@PathVariable(value = "id") Long productId){
        return reviewRepository.findByProductId(productRepository.getOne(productId));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/products/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Review createReview(HttpServletRequest request, @Valid @RequestBody Review review){
        return reviewRepository.save(review);
    }

//    @RequestMapping(value = "/products/reviews", method = RequestMethod.POST)
//    public Review createReview(Review review, User user){
//        User tempUser = userRepository.getOne(user.getId());
//        review.setUser(tempUser);
//        return reviewRepository.save(review);
//    }
}
