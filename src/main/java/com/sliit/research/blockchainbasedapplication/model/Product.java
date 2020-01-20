package com.sliit.research.blockchainbasedapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sliit.research.blockchainbasedapplication.constants.ProductType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Double farmerPrice;
    private Double retailPrice;
    private Double wholeSalePrice;

    private ProductType productType;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;
    private Date expiryDate;
    private boolean approved;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFarmerPrice() {
        return farmerPrice;
    }

    public void setFarmerPrice(Double farmerPrice) {
        this.farmerPrice = farmerPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isApproved() {
        return approved;
    }

    public Double getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(Double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
