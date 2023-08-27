package com.onlinefoodcommercems.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Surname can't be empty")
    private String surname;
    @Size(min = 6, max = 16, message = "The password be between 6 and 16 characters long")
    private String password;
    @Size(min = 6, max = 16, message = "The password be between 6 and 16 characters long")
    private String passwordResetCode;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email cannot be empty")
    private String username;


}
