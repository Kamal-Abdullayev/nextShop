package com.nextShop.user_service.controller;

import com.nextShop.user_service.dto.ProceedKey;
import com.nextShop.user_service.dto.UserCreatRequestDto;
import com.nextShop.user_service.dto.payload.LoginPayload;
import com.nextShop.user_service.dto.payload.RefreshTokenPayload;
import com.nextShop.user_service.dto.response.LoginResponse;
import com.nextShop.user_service.exception.BaseResponse;
import com.nextShop.user_service.service.AuthBusinessService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload) {
        return BaseResponse.success(authBusinessService.login(payload));
    }

    @PostMapping("/token/refresh")
    public BaseResponse<LoginResponse> refresh(@RequestBody RefreshTokenPayload payload) {
        return BaseResponse.success(authBusinessService.refresh(payload));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout() {
        authBusinessService.logout();
        return BaseResponse.success();
    }


    @PostMapping("/sign-up")
    public BaseResponse<ProceedKey> signUp(@RequestBody UserCreatRequestDto userCreatRequestDto) {
        return BaseResponse.success(authBusinessService.signUp(userCreatRequestDto));
    }

}
