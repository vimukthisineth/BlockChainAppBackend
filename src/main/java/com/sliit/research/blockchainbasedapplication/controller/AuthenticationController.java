package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.service.AuthenticationService;
import com.sliit.research.blockchainbasedapplication.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse attemptLogin(HttpServletRequest request, @Valid @RequestBody User user){
        return authenticationService.login(user);
    }

    @PostMapping("/signup")
    public AuthResponse attemptSignup(HttpServletRequest request, @Valid @RequestBody User user){
        return authenticationService.signUp(user);
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestParam("user") User user, @RequestParam("token") String token){
        return authenticationService.validateToken(user.getId(), token);
    }
}
