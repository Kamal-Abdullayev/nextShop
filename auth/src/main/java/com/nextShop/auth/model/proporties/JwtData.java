package com.nextShop.auth.model.proporties;

import lombok.Data;

@Data
public class JwtData {

    private String publicKey;
    private String privateKey;
    private int accessTokenValidityTime;
    private int refreshTokenValidityTime;

    public Long getRefreshTokenValidityTime(boolean rememberMe) {
        return refreshTokenValidityTime * (rememberMe ? 30L : 1L);
    }

}
