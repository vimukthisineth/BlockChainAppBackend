package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.model.Product;
import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.ReviewRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserActivityRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;

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

    @Override
    public String getSentimentalAnalysis(String review) throws IOException {
        try {
            String requestUrl = "http://127.0.0.1:8083/NLP?text="+review.replace(" ", "%20");
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null ){
                content.append(inputLine);
            }
            String response = content.toString();
            return response;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getAspectAnalysis(String review) throws IOException {
        try {
            String requestUrl = "http://127.0.0.1:8084/NlpAspect?text="+review.replace(" ", "%20");
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null ){
                content.append(inputLine);
            }
            String response = content.toString();
            return response;
        }catch (Exception e){
            return null;
        }
    }
}
