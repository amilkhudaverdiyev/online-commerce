package com.onlinefoodcommercems.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordResetRequest {
    private String username;
    @Size(min = 6,max = 16,message = "The password be between 6 and 16 characters long")
    private String password;
    @Size(min = 6,max = 16,message = "The password be between 6 and 16 characters long")
    private String passwordResetCode;
}
