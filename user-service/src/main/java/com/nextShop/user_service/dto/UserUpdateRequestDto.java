package com.nextShop.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDto {
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank
    private String phoneNumber;

    // TODO: Add other fields

}
