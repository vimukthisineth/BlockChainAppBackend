package com.sliit.research.blockchainbasedapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliit.research.blockchainbasedapplication.blockChain.Block;
import com.sliit.research.blockchainbasedapplication.blockChain.BlockChain;
import com.sliit.research.blockchainbasedapplication.blockChain.BlockData;
import com.sliit.research.blockchainbasedapplication.constants.LogTypes;
import com.sliit.research.blockchainbasedapplication.constants.ProductType;
import com.sliit.research.blockchainbasedapplication.exception.ResourceNotFoundException;
import com.sliit.research.blockchainbasedapplication.model.*;
import com.sliit.research.blockchainbasedapplication.repository.ProductRepository;
import com.sliit.research.blockchainbasedapplication.repository.ReviewRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserActivityRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.BlockChainService;
import com.sliit.research.blockchainbasedapplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BlockChainService blockChainService;

    @Autowired
    UserActivityRepository userActivityRepository;

    @PostMapping("/products")
    public Product createProduct(HttpServletRequest request, @Valid @RequestBody Product product) throws JsonProcessingException {
        BlockChain blockChain = BlockChain.getInstance();
        BlockData blockData = new BlockData();
        blockData.setDate(new Date());
        if (product.getProductType() == ProductType.MANUFACTURER || product.getProductType() == ProductType.FARMER){
            String[] nameWords = product.getName().split(" ");
            List<Product> farmerProducts = productRepository.findByProductType(ProductType.FARMER);
            for (Product farmerProduct : farmerProducts){
                for (int i = 0; i < nameWords.length; i++) {
                    if (farmerProduct.getName().toLowerCase().contains(nameWords[i].toLowerCase())){
                        product.setFarmerId(farmerProduct.getUser().getId());
                        product.setFarmedDate(farmerProduct.getFarmedDate());
                        product.setHarvestedDate(farmerProduct.getHarvestedDate());
                        product.setExpiryDate(farmerProduct.getExpiryDate());
                        break;
                    }
                }
            }
            if (product.getProductType() == ProductType.FARMER){
                product.setHarvestedDate(new Date());
                product.setFarmerId(product.getUser().getId());
            }
            if (product.getId() > 0){
                Product productOnDb = productRepository.findById(product.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", product));
                if (!productOnDb.isApproved() && product.isApproved()){
                    product.setApprovedDate(new Date());
                    blockData.setBlockMessage("Approved on: "+new Date().toString());
                }else if (productOnDb.isApproved() && !product.isApproved()){
                    product.setDisApprovedDate(new Date());
                    blockData.setBlockMessage("Disapproved on: "+new Date().toString());
                }else {
                    product.setManufacturedDate(new Date());
                    blockData.setBlockMessage("Manufactured on: "+new Date().toString());
                }
            }
        }
        Product savedProduct = productRepository.save(product);
        blockData.setProduct(savedProduct.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        Block block = null;
        if (blockChain.getBlockChainSize() >= 0){
            block = new Block(objectMapper.writeValueAsString(blockData), blockChain.getBlockChain().get(blockChain.getBlockChainSize()-1).hash);
        }else {
            block = new Block(objectMapper.writeValueAsString(blockData), blockChain.getBlockChain().get(blockChain.getBlockChainSize()).hash);
        }
        blockChain.addBlock(block);
        // Additional DB backup
        blockChainService.addBlock(block);
        return savedProduct;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> productsFromDb = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (int i = (productsFromDb.size() - 1); i > -1; i--){
            products.add(productsFromDb.get(i));
        }
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @GetMapping("/products/reviewsByProduct/{id}")
    public List<Review> getReviewsByProductId(@PathVariable(value = "id") Long productId){
        return reviewService.findByProductId(productId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/products/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Review createReview(HttpServletRequest request, @Valid @RequestBody Review review) throws IOException {
//        review.setSentiment(reviewService.getSentimentalAnalysis(review.getContent()));
        if (review.getContent().length() > 20 && (review.getContent().toLowerCase().contains("price") || review.getContent().toLowerCase().contains("quality") || review.getContent().toLowerCase().contains("good") || review.getContent().toLowerCase().contains("bad"))){
            review.setSentiment(reviewService.getSentimentalAnalysis(review.getContent()));
        }else {
            review.setSentiment("Neutral");
        }
        review.setAspect(reviewService.getAspectAnalysis(review.getContent()));

        UserActivity userActivity = new UserActivity(
                LogTypes.REVIEW,
                new Date(),
                review.getUser().getId(),
                review.getProduct().getId(),
                review.getId()
        );
        userActivityRepository.save(userActivity);
        return reviewRepository.save(review);
    }

//    @RequestMapping(value = "/products/reviews", method = RequestMethod.POST)
//    public Review createReview(Review review, User user){
//        User tempUser = userRepository.getOne(user.getId());
//        review.setUser(tempUser);
//        return reviewRepository.save(review);
//    }
}
