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
public class UserCreatRequestDto {
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
    @NotBlank
    @Size(min = 8, message = "Password cannot be shorter than 8 characters")
    private String password;
    // TODO: Better password validation
    // TODO: Add other fields

}
