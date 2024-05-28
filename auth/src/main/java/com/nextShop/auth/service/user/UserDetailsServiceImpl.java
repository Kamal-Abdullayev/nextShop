package com.nextShop.auth.service.user;

import com.nextShop.auth.model.CustomUserDetail;
import com.nextShop.auth.model.User;
import com.nextShop.auth.repository.UserRepository;
import com.nextShop.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        return new CustomUserDetail(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
