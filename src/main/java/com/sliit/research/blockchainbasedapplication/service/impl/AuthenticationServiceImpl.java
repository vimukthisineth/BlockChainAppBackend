package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.constants.AuthResponseCodes;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.AuthenticationService;
import com.sliit.research.blockchainbasedapplication.utils.AuthResponse;
import com.sliit.research.blockchainbasedapplication.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private RandomString randomString = new RandomString(64);

    @Override
    public AuthResponse login(User login) {
        List<User> usersFromDb = userRepository.findByEmail(login.getEmail());
        if (usersFromDb.size() > 0){
            User user = usersFromDb.get(0);
            if (user.getPassword().equals(passwordEncoder.encode(login.getPassword()))){
                user.setPassword(null);
                user.setToken(randomString.nextString());
                userRepository.save(user);
                return new AuthResponse(user, AuthResponseCodes.SUCCESS);
            }else {
                login.setPassword(null);
                return new AuthResponse(login, AuthResponseCodes.PASSWORD_WRONG);
            }
        }else {
            login.setPassword(null);
            return new AuthResponse(login, AuthResponseCodes.USER_NOT_FOUND);
        }
    }

    @Override
    public AuthResponse signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).size() > 0){
            return new AuthResponse(user, AuthResponseCodes.EMAIL_ALREADY_EXIST);
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            user.setPassword(null);
            return new AuthResponse(user, AuthResponseCodes.SUCCESS);
        }
    }
}