package com.sliit.research.blockchainbasedapplication.dto;

import com.sliit.research.blockchainbasedapplication.model.User;

public class UserValidDto {
    private User user;
    private boolean valid;

    public UserValidDto(User user, boolean valid) {
        this.user = user;
        this.valid = valid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
