package com.sliit.research.blockchainbasedapplication.controller;

import com.sliit.research.blockchainbasedapplication.dto.UserValidDto;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.repository.UserRepository;
import com.sliit.research.blockchainbasedapplication.service.AuthenticationService;
import com.sliit.research.blockchainbasedapplication.service.BlockChainService;
import com.sliit.research.blockchainbasedapplication.service.UserActivityService;
import com.sliit.research.blockchainbasedapplication.utils.AuthResponse;
import com.sliit.research.blockchainbasedapplication.utils.LoginSignup;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserActivityService userActivityService;

    @Autowired
    BlockChainService blockChainService;

    @PostMapping("/login")
    public AuthResponse attemptLogin(HttpServletRequest request, @Valid @RequestBody LoginSignup loginSignup){
        User user = new User(
                loginSignup.getFirstName(),
                loginSignup.getLastName(),
                loginSignup.getEmail(),
                loginSignup.getUserType(),
                loginSignup.getAddress(),
                loginSignup.getPassword()
        );
        blockChainService.getBlockChainFromDb();
        return authenticationService.login(user);
    }

    @PostMapping("/signup")
    public AuthResponse attemptSignup(HttpServletRequest request, @Valid @RequestBody LoginSignup loginSignup){
        User user = new User(
                loginSignup.getFirstName(),
                loginSignup.getLastName(),
                loginSignup.getEmail(),
                loginSignup.getUserType(),
                loginSignup.getAddress(),
                loginSignup.getPassword()
        );
        return authenticationService.signUp(user);
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestParam("user") User user, @RequestParam("token") String token){
        return authenticationService.validateToken(user, token);
    }

    @PostMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/usersValidated")
    public List<UserValidDto> getAllUsersValidated() throws IOException, ParseException {
        List<User> users = userRepository.findAll();
        List<UserValidDto> userValidDtos = new ArrayList<>();
        for (User user : users){
            boolean valid = userActivityService.isUserReal(user);
            UserValidDto userValidDto = new UserValidDto(user, valid);
            userValidDtos.add(userValidDto);
        }
        return userValidDtos;
    }

}
