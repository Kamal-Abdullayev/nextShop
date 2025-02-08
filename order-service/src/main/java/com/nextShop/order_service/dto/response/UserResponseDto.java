package com.nextShop.order_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;


}
