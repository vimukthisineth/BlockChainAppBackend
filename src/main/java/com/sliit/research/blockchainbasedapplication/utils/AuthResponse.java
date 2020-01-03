package com.sliit.research.blockchainbasedapplication.utils;

import com.sliit.research.blockchainbasedapplication.constants.AuthResponseCodes;
import com.sliit.research.blockchainbasedapplication.model.User;

public class AuthResponse {
    private User user;
    private AuthResponseCodes authResponseCodes;
    private String token;

    public AuthResponse(User user, AuthResponseCodes authResponseCodes, String token) {
        this.user = user;
        this.authResponseCodes = authResponseCodes;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthResponseCodes getAuthResponseCodes() {
        return authResponseCodes;
    }

    public void setAuthResponseCodes(AuthResponseCodes authResponseCodes) {
        this.authResponseCodes = authResponseCodes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
