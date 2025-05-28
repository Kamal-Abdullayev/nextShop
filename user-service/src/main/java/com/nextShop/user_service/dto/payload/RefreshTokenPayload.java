package com.nextShop.user_service.dto.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenPayload {

    private String refreshToken;
    private boolean rememberMe;

}
