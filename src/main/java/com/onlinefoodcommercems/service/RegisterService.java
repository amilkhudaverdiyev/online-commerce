package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;

public interface RegisterService {
    String register(CustomerRequest registrationRequest);
     String confirmToken(String token);
}
