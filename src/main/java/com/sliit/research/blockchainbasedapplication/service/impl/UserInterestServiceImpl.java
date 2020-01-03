package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.model.UserInterest;
import com.sliit.research.blockchainbasedapplication.repository.UserInterestRepository;
import com.sliit.research.blockchainbasedapplication.service.UserInterestService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInterestServiceImpl implements UserInterestService {

    @Autowired
    private UserInterestRepository userInterestRepository;

    @Override
    public UserInterest find(Long id) {
        return userInterestRepository.getOne(id);
    }
}
