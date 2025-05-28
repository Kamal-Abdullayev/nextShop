package com.nextShop.user_service.service;

import com.nextShop.user_service.dto.ProceedKey;
import com.nextShop.user_service.dto.UserCreatRequestDto;
import com.nextShop.user_service.dto.response.LoginResponse;
import com.nextShop.user_service.dto.payload.LoginPayload;
import com.nextShop.user_service.dto.payload.RefreshTokenPayload;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload payload);

    LoginResponse refresh(RefreshTokenPayload payload);

    void logout();

    ProceedKey signUp(UserCreatRequestDto userCreatRequestDto);

    void setAuthentication(String email);

}