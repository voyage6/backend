package com.mini.backend.security;

import com.mini.backend.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private final User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getUserId() {
        return user.getUserId();
    }

    @Override
    public String getUserPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUserName() {
        return user.getUserName();
    }
}
