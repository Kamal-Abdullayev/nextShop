package com.nextShop.auth.service;

import com.nextShop.auth.dto.CreateUserRequest;
import com.nextShop.auth.models.Users;
import com.nextShop.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Users> getByUserName(String userName) {
         return userRepository.findByUsername(userName);
     }


     public Users createUser(CreateUserRequest createUserRequest) {
        Users newUser = Users.builder()
                .name(createUserRequest.name())
                .surname(createUserRequest.surname())
                .username(createUserRequest.username())
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .phoneNumber(createUserRequest.phoneNumber())
                .accountNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        return userRepository.save(newUser);
     }

}
