package com.nextShop.auth.service;

import com.nextShop.auth.dto.response.LoginResponse;
import com.nextShop.auth.payload.LoginPayload;
import com.nextShop.auth.payload.RefreshTokenPayload;

public interface AuthBusinessService {
    LoginResponse login(LoginPayload loginPayload);
    LoginResponse refreshToken(RefreshTokenPayload refreshTokenPayload);

}
