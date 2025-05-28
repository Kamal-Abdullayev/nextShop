package com.nextShop.user_service.service;

import com.nextShop.user_service.dto.LoggedInUserDetails;
import com.nextShop.user_service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    // todo: currently our system supports only email login, but in the future
    //  we have to implement phone number version
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getByEmail(username);

//        throwIf(()-> !user.isActive(),BaseException.of(USER_NOT_ACTIVE));

        return new LoggedInUserDetails(
                user.getEmail(), user.getPassword(), new ArrayList<>()
        );
    }
}
