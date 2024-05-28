package com.nextShop.auth.service.security;

import com.nextShop.auth.dto.RefreshTokenDto;
import com.nextShop.auth.model.User;
import com.nextShop.auth.model.proporties.SecurityProperties;
import com.nextShop.auth.util.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>, TokenReader<Claims>{
    private final SecurityProperties securityProperties;

    @Override
    public String generate(RefreshTokenDto obj) {
        final User user = obj.getUser();

        Claims claims = Jwts.claims();
        claims.put("userName", user.getUsername());
        claims.put("type", "REFRESH_TOKEN");

        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime()
                + securityProperties.getJwt().getRefreshTokenValidityTime(obj.isRememberMe()));


        return Jwts.builder()
                .setSubject(user.getFirstName())
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
