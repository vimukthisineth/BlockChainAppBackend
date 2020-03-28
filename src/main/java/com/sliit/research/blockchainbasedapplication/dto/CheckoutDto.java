package com.sliit.research.blockchainbasedapplication.dto;

import com.sliit.research.blockchainbasedapplication.model.User;

public class CheckoutDto {

    private User user;
    private String address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
