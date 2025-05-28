package com.nextShop.user_service.security.filter;

import com.nextShop.user_service.constant.TokenConstants;
import com.nextShop.user_service.exception.BaseException;
import com.nextShop.user_service.security.AccessTokenManager;
import com.nextShop.user_service.service.AuthBusinessService;
import com.nextShop.user_service.service.TokenBlacklistService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (Objects.nonNull(token) && token.startsWith(TokenConstants.PREFIX)) {
                String actualToken = token.substring(7);

                if (tokenBlacklistService.isTokenBlacklisted(actualToken)) {
                    log.warn("Token is blacklisted: {}", actualToken);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                String email = accessTokenManager.getEmail(actualToken);
                authBusinessService.setAuthentication(email);
            }
        } catch (BaseException | JwtException ex) {
            log.warn(Arrays.toString(ex.getStackTrace()));
        } catch (Exception ex) {
            log.error(Arrays.toString(ex.getStackTrace()));
        }

        filterChain.doFilter(request, response);
    }
}