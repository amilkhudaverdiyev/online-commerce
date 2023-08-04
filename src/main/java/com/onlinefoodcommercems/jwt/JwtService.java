package com.onlinefoodcommercems.jwt;

import com.onlinefoodcommercems.entity.Customer;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String createJwt(Customer user);

    Claims verifyJwt(String token);
}
