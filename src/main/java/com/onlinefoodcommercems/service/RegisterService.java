package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.request.PasswordResetRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;

public interface RegisterService {
    void register(CustomerRequest registrationRequest);

    void registerAdmin(CustomerRequest registrationRequest);

    String confirmToken(String token);

    AuthenticationResponse login(AuthenticationRequest request);

    void changePassword(String email, PasswordResetRequest request);

    String forgotPassword(String username);

    void setPassword(String username, String code, String newPassword);
}
