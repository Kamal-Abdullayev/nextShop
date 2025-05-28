package com.nextShop.user_service.dto;

import com.nextShop.user_service.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenDto {

    private boolean rememberMe;
    private User user;

}
