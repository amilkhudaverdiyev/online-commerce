package com.onlinefoodcommercems.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordResetRequest {

    @Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters long")
    @NotBlank(message = "Old password is not empty")
    private String oldPassword;
    @Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters long")
    @NotBlank(message =  "New password is not empty")
    private String newPassword;

    @Size(min = 3, max = 20, message = "The password confirmation must be between 3 and 20 characters long")
    @NotNull(message = "Repeat password is not empty")
    private String repeatPassword;
}

