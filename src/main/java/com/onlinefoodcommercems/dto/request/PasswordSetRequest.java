package com.onlinefoodcommercems.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordSetRequest {
    @Email(message = "Email incorrect")
    @NotBlank(message = "Email is not empty")
    private String email;
    private String otp;
    @Size(min = 6, max = 20, message = "The password must be between 6 and 10 characters long")
    @NotBlank(message = "Password is not empty")
    private String password;

}

