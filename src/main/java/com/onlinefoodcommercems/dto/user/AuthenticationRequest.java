package com.onlinefoodcommercems.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    @Email(message = "Incorrect email!")
    @NotBlank(message = "Email cannot be empty")
    private String username;
    @Size(min = 6, max = 20, message = "Password contains 6-20 characters")
    @NotBlank(message = "Password is not empty")
    private String password;
}
