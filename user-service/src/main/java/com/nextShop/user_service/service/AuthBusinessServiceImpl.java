package com.nextShop.user_service.service;

import com.nextShop.user_service.constant.TokenConstants;
import com.nextShop.user_service.dto.ProceedKey;
import com.nextShop.user_service.dto.RefreshTokenDto;
import com.nextShop.user_service.dto.UserCreatRequestDto;
import com.nextShop.user_service.dto.response.LoginResponse;
import com.nextShop.user_service.exception.BaseException;
import com.nextShop.user_service.exception.ErrorResponseMessages;
import com.nextShop.user_service.model.User;
import com.nextShop.user_service.dto.payload.LoginPayload;
import com.nextShop.user_service.dto.payload.RefreshTokenPayload;
import com.nextShop.user_service.security.AccessTokenManager;
import com.nextShop.user_service.security.RefreshTokenManager;
import com.nextShop.user_service.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public LoginResponse login(LoginPayload payload) {

        authenticate(payload);

        return prepareLoginResponse(payload.getEmail(), payload.isRememberMe());
    }

    @Override
    public LoginResponse refresh(RefreshTokenPayload payload) {
        return prepareLoginResponse(
                refreshTokenManager.getEmail(payload.getRefreshToken()),
                payload.isRememberMe()
        );
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith(TokenConstants.PREFIX)) {
            String actualToken = token.substring(7);
            long expiration = getTokenExpiration(actualToken);
            tokenBlacklistService.blacklistToken(actualToken, expiration);

            log.info("{} user logged out, token blacklisted", authentication.getName());
        }
    }

    private long getTokenExpiration(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (claims.getExpiration().getTime() - System.currentTimeMillis()) / 1000;
    }

    @Override
    public ProceedKey signUp(UserCreatRequestDto userCreatRequestDto) {


        if (userService.checkByEmail(userCreatRequestDto.getEmail())) {
            throw BaseException.of(ErrorResponseMessages.EMAIL_ALREADY_REGISTERED);
        }

//        Role defaultRole = roleService.getDefaultRole();

        userService.createUser(userCreatRequestDto);

        return ProceedKey.builder().proceedKey("").build();
    }



    @Override
    public void setAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities())
        );
    }


    private void authenticate(LoginPayload request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw e.getCause() instanceof BaseException ?
                    (BaseException) e.getCause() :
                    BaseException.unexpected();
        }
    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {
        User user = userService.getByEmail(email);

        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()
                ))
                .build();
    }

}