package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.utils.AuthResponse;

public interface AuthenticationService {
    AuthResponse login(User user);

    AuthResponse signUp(User user);

    boolean validateToken(User user, String token);

}
