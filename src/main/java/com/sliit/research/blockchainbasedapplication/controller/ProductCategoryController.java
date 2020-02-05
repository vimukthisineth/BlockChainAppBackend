package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.model.ProductCategory;
import com.sliit.research.blockchainbasedapplication.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("/productCategories")
    public List<ProductCategory> getAll(){
        return productCategoryRepository.findAll();
    }

}
