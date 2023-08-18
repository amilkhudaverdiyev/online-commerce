package com.onlinefoodcommercems.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordSetRequest {


    @Email
    private String email;
    private  Integer otp;
    @Size(min = 3, max = 20, message = "The password must be between 3 and 20 characters long")
    private String password;

}

