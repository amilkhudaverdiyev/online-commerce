package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;

public interface RegisterService {
    void register(CustomerRequest registrationRequest);
     String confirmToken(String token);
     AuthenticationResponse login(AuthenticationRequest request);
}
