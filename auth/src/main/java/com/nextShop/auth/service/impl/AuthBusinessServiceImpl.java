package com.nextShop.auth.service.impl;

import com.nextShop.auth.dto.RefreshTokenDto;
import com.nextShop.auth.dto.response.LoginResponse;
import com.nextShop.auth.model.User;
import com.nextShop.auth.payload.LoginPayload;
import com.nextShop.auth.payload.RefreshTokenPayload;
import com.nextShop.auth.service.AuthBusinessService;
import com.nextShop.auth.service.UserService;
import com.nextShop.auth.service.security.AccessTokenManager;
import com.nextShop.auth.service.security.RefreshTokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthBusinessServiceImpl implements AuthBusinessService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;


    @Override
    public LoginResponse login(LoginPayload loginPayload) {
        authenticate(loginPayload);
        return prepareResponse(loginPayload.getUsername(), loginPayload.isRememberMe());
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenPayload refreshTokenPayload) {
        Claims claims = refreshTokenManager.read(refreshTokenPayload.getRefreshToken());
        final String username = claims.get("username", String.class);
        return prepareResponse(username, refreshTokenPayload.isRememberMe());
    }

    private void authenticate(LoginPayload loginPayload) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginPayload.getUsername(), loginPayload.getPassword())
            );
        } catch (AuthenticationException e) {
            // todo: change exception
            throw new RuntimeException("Exception occurred");
        }
    }

    private LoginResponse prepareResponse(String username, boolean rememberMe) {
        User user = userService.getUserByUsername(username);
        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()
                ))
                .build();
    }
}
