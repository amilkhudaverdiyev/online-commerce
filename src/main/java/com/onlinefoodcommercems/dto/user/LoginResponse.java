package com.onlinefoodcommercems.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {
    String username;
    String name;
    String surname;
    String token;
}
