package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.model.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface UserActivityService {
    boolean isUserReal(User user) throws IOException, ParseException;
}
