package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.constants.AuthResponseCodes;
import com.sliit.research.blockchainbasedapplication.constants.LogTypes;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.model.UserActivity;
import com.sliit.research.blockchainbasedapplication.repository.UserActivityRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.AuthenticationService;
import com.sliit.research.blockchainbasedapplication.utils.AuthResponse;
import com.sliit.research.blockchainbasedapplication.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;

    private RandomString randomString = new RandomString(64);

    @Override
    public AuthResponse login(User login) {
        List<User> usersFromDb = userRepository.findByEmail(login.getEmail());
        if (usersFromDb.size() > 0){
            User user = usersFromDb.get(0);
            if (user.getPassword().equals(login.getPassword())){
                user.setToken(randomString.nextString());
                userRepository.save(user);
                UserActivity userActivity = new UserActivity(
                        LogTypes.LOGIN,
                        new Date(),
                        user.getId(),
                        null,
                        null
                );
                userActivityRepository.save(userActivity);
                user.setPassword(null);
                return new AuthResponse(user, AuthResponseCodes.SUCCESS, user.getToken(), user.getUserType());
            }else {
                login.setPassword(null);
                return new AuthResponse(login, AuthResponseCodes.PASSWORD_WRONG, null);
            }
        }else {
            login.setPassword(null);
            return new AuthResponse(login, AuthResponseCodes.USER_NOT_FOUND, null);
        }
    }

    @Override
    public AuthResponse signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).size() > 0){
            return new AuthResponse(user, AuthResponseCodes.EMAIL_ALREADY_EXIST, null);
        }else {
            user.setPassword(user.getPassword());
            userRepository.save(user);
            user.setPassword(null);
            return new AuthResponse(user, AuthResponseCodes.SUCCESS, null);
        }
    }

    @Override
    public boolean validateToken(User user, String token) {
        if (user == null){
            return false;
        }
        List<User> fromDb = userRepository.findByIdAndToken(user.getId(), token);
        if (fromDb.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
