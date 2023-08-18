package com.onlinefoodcommercems.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    @Email(message = "Incorrect email!")
    @NotBlank(message = "Email cannot be empty")

    private String username;
    @Size(min = 3, max = 20, message = "Password contains 3-20 characters")
    private String password;
}
