package com.nextShop.auth.service.impl;

import com.nextShop.auth.model.User;
import com.nextShop.auth.repository.UserRepository;
import com.nextShop.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                //todo: change exception
                () -> new RuntimeException("User with email " + email + " not found")
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(
                //todo: change exception
                () -> new RuntimeException("User with username " + username + " not found")
        );
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
