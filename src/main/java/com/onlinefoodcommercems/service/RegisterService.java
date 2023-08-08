package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.request.PasswordResetRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;

public interface RegisterService {
    void register(CustomerRequest registrationRequest);
     String confirmToken(String token);
     AuthenticationResponse login(AuthenticationRequest request);
    String changePassword(String email, PasswordResetRequest request);

    String forgotPassword(String username);

    String setPassword(String username,Integer code, String newPassword);
}
