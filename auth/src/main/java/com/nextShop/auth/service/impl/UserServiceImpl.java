package com.nextShop.auth.service.impl;

import com.nextShop.auth.dto.UserCreateRequest;
import com.nextShop.auth.model.User;
import com.nextShop.auth.repository.UserRepository;
import com.nextShop.auth.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    public User createUser(UserCreateRequest userCreateRequest) {
        User newUser = User.builder()
                .name(userCreateRequest.name())
                .surname(userCreateRequest.surname())
                .email(userCreateRequest.email())
                .phoneNumber(userCreateRequest.phoneNumber())
                .password(encoder.encode(userCreateRequest.password()))
                .accountNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .credentialNonExpired(true)
                .isCredentialsNonExpired(true)
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }


}
