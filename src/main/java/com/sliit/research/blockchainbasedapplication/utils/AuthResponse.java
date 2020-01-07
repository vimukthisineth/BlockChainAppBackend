package com.sliit.research.blockchainbasedapplication.utils;

import com.sliit.research.blockchainbasedapplication.constants.AuthResponseCodes;
import com.sliit.research.blockchainbasedapplication.constants.UserTypes;
import com.sliit.research.blockchainbasedapplication.model.User;

public class AuthResponse {
    private User user;
    private AuthResponseCodes authResponseCode;
    private String token;
    private UserTypes userType;

    public AuthResponse(User user, AuthResponseCodes authResponseCodes, String token) {
        this.user = user;
        this.authResponseCode = authResponseCodes;
        this.token = token;
    }

    public AuthResponse(User user, AuthResponseCodes authResponseCode, String token, UserTypes userType) {
        this.user = user;
        this.authResponseCode = authResponseCode;
        this.token = token;
        this.userType = userType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthResponseCodes getAuthResponseCode() {
        return authResponseCode;
    }

    public void setAuthResponseCode(AuthResponseCodes authResponseCode) {
        this.authResponseCode = authResponseCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }
}
