package com.nextShop.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginPayload {
    // todo: validation
    private String username;
    private String password;
    private boolean rememberMe;
}
