package com.sliit.research.blockchainbasedapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sliit.research.blockchainbasedapplication.constants.UserTypes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private UserTypes userType;
    private String address;

    @JsonIgnore
    private String password;


    @JsonIgnore
    private String token;

//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserInterest> userInterests;

    public User() {
    }

    public User(String firstName, String lastName, String email, UserTypes userType, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.address = address;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public List<UserInterest> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(List<UserInterest> userInterests) {
        this.userInterests = userInterests;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }
}
