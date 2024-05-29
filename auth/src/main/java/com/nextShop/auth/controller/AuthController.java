package com.nextShop.auth.controller;

import com.nextShop.auth.dto.RefreshTokenDto;
import com.nextShop.auth.dto.response.LoginResponse;
import com.nextShop.auth.model.User;
import com.nextShop.auth.model.base.BaseResponse;
import com.nextShop.auth.payload.LoginPayload;
import com.nextShop.auth.payload.RefreshTokenPayload;
import com.nextShop.auth.service.AuthBusinessService;
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
    private final AuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload) {
        return BaseResponse.success(authBusinessService.login(loginPayload));
    }

    @PostMapping("/token/refresh")
    public BaseResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenPayload refreshTokenPayload) {

        return BaseResponse.success(authBusinessService.refreshToken(refreshTokenPayload)) ;
    }



}
