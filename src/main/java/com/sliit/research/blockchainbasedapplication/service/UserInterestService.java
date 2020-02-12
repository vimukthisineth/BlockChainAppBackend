package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.model.UserInterest;
import org.springframework.stereotype.Service;

public interface UserInterestService {
    UserInterest find(Long id);
}
