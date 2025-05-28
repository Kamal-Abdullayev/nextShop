package com.nextShop.user_service.dto.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPayload {

    String email;
    String password;
    boolean rememberMe;

}
