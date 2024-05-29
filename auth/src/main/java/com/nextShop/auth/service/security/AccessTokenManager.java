package com.nextShop.auth.service.security;

import com.nextShop.auth.model.User;
import com.nextShop.auth.model.proporties.SecurityProperties;
import com.nextShop.auth.repository.UserRepository;
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
public class AccessTokenManager implements TokenGenerator<User>, TokenReader<Claims> {

    private final SecurityProperties securityProperties;
    @Override
    public String generate(User user) {
        Claims claims = Jwts.claims();
        claims.put("username", user.getUsername());
        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + securityProperties.getJwt().getAccessTokenValidityTime());


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
