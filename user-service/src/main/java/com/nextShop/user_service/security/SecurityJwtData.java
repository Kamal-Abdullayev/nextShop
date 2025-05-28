package com.nextShop.user_service.security;

import lombok.Data;

@Data
public class SecurityJwtData {

    private String publicKey;
    private String privateKey;
    private Integer accessTokenValidityTime;
    private Integer refreshTokenValidityTime;

    public Long getRefreshTokenValidityTime(boolean rememberMe) {
        return refreshTokenValidityTime * (rememberMe ? 30L : 1L);
    }

}