package com.nextShop.user_service.dto.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpPayload {

    String name;
    String surname;
    String email;
    String phoneNumber;
    String password;
    String courseName;
    String address;

}
