package com.nextShop.user_service.service;

import com.nextShop.user_service.dto.UserCreatRequestDto;
import com.nextShop.user_service.dto.UserResponseDto;
import com.nextShop.user_service.dto.UserUpdateRequestDto;
import com.nextShop.user_service.exception.BaseException;
import com.nextShop.user_service.exception.InvalidParameterException;
import com.nextShop.user_service.exception.ResourceNotFoundException;
import com.nextShop.user_service.model.User;
import com.nextShop.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getAllUsers(Pageable pageable) {
        log.info("Get all users");
        return userRepository.findAll(pageable).stream()
                .map(UserResponseDto::convert)
                .toList();
    }

    public UserResponseDto getUserById(String userId) {
        log.info("Get user by id: " + userId);
        return UserResponseDto.convert(getUserObjectById(userId));
    }

    public User getUserObjectById(String userId) {
        if (userId == null || userId.isEmpty()) {
            log.error("user id is null or empty");
            throw new InvalidParameterException("User id is null or empty");
        }
        log.info("Get user object by id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    public String createUser(UserCreatRequestDto userCreatRequestDto) {
        User newUser = User.builder()
                .firstName(userCreatRequestDto.getFirstName())
                .lastName(userCreatRequestDto.getLastName())
                .username(userCreatRequestDto.getUsername())
                .email(userCreatRequestDto.getEmail())
                .phoneNumber(userCreatRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(userCreatRequestDto.getPassword()))
                .build();

        log.info("Create user: {}", newUser);
        return userRepository.save(newUser).getId();
    }

    public UserResponseDto updateUser(String userId, UserUpdateRequestDto updateRequestDto) {
        log.info("Update user by id: " + userId);
        User dbUser = getUserObjectById(userId);
        log.info("Update user object: {}", dbUser);

        if (!dbUser.getFirstName().equals(updateRequestDto.getFirstName())) {
            log.info("Update user first name: " + dbUser.getFirstName());
            dbUser.setFirstName(updateRequestDto.getFirstName());
        }
        if (!dbUser.getLastName().equals(updateRequestDto.getLastName())) {
            log.info("Update user last name: " + dbUser.getLastName());
            dbUser.setLastName(updateRequestDto.getLastName());
        }
        if (!dbUser.getUsername().equals(updateRequestDto.getUsername())) {
            log.info("Update user username: " + dbUser.getUsername());
            dbUser.setUsername(updateRequestDto.getUsername());
        }
        if (!dbUser.getPhoneNumber().equals(updateRequestDto.getPhoneNumber())) {
            log.info("Update user phone number: " + dbUser.getPhoneNumber());
            dbUser.setPhoneNumber(updateRequestDto.getPhoneNumber());
        }
        if (!dbUser.getEmail().equals(updateRequestDto.getEmail())) {
            log.info("Update user email: " + dbUser.getEmail());
            dbUser.setEmail(updateRequestDto.getEmail());
        }

        User updatedUser = userRepository.save(dbUser);
        log.info("User updated successfully: {}", updatedUser);
        return UserResponseDto.convert(updatedUser);
    }

    public void deleteUserById(String userId) {
        User user = getUserObjectById(userId);
        userRepository.delete(user);
        log.info("User deleted successfully with id: {}", userId);
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> BaseException.notFound(User.class.getSimpleName(), "email", email)
        );
    }

    public boolean checkByEmail(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }



}
