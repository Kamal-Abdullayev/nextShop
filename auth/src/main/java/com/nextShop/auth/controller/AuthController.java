package com.nextShop.auth.controller;

import com.nextShop.auth.dto.RefreshTokenDto;
import com.nextShop.auth.dto.response.LoginResponse;
import com.nextShop.auth.model.User;
import com.nextShop.auth.model.base.BaseResponse;
import com.nextShop.auth.payload.LoginPayload;
import com.nextShop.auth.service.UserService;
import com.nextShop.auth.service.security.AccessTokenManager;
import com.nextShop.auth.service.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload) {

        authenticate(loginPayload);
        User user = userService.getUserByEmail("kamal2@gmail.com");

        return BaseResponse.success(
                LoginResponse.builder()
                        .accessToken(accessTokenManager.generate(user))
                        .refreshToken(refreshTokenManager.generate(
                                RefreshTokenDto.builder()
                                        .user(user)
                                        .rememberMe(loginPayload.isRememberMe())
                                        .build()
                        ))
                        .build()
        );
    }


    private final AuthenticationManager authenticationManager;
    private void authenticate(LoginPayload loginPayload) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginPayload.getUsername(), loginPayload.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Exception occurred");
        }

    }


}
