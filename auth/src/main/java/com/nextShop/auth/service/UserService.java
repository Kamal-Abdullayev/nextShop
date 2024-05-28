package com.nextShop.auth.service;

import com.nextShop.auth.model.User;

public interface UserService {
    User getUserByEmail(String email);
    User getUserByUsername(String userName);
    void saveUser(User user);
}
