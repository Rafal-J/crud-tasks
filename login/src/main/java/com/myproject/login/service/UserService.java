package com.myproject.login.service;

import com.myproject.login.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
