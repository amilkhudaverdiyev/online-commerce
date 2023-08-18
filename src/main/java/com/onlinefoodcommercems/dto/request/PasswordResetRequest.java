package com.onlinefoodcommercems.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordResetRequest {

    @Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters long")
    private String oldPassword;
    @Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters long")
    private String newPassword;

    @Size(min = 3, max = 20, message = "The password confirmation must be between 3 and 20 characters long")
    private String repeatPassword;
}

