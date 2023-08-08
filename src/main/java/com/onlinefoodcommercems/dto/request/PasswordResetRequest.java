package com.onlinefoodcommercems.dto.request;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordResetRequest {

    private String email;
    @Size(min = 6, max = 16, message = "The password must be between 6 and 16 characters long")
    private String oldPassword;
    @Size(min = 6, max = 16, message = "The password must be between 6 and 16 characters long")
    private String password;

    @Size(min = 6, max = 16, message = "The password confirmation must be between 6 and 16 characters long")
    private String repeatPassword;
}

