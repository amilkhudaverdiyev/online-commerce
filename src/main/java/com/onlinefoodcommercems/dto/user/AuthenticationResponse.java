package com.onlinefoodcommercems.dto.user;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String username;
    private String token;
    private String userRole;
}
