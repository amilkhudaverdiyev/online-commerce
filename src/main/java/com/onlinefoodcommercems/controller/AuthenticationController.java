package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.LoginResponse;
import com.onlinefoodcommercems.service.RegisterService;
import com.onlinefoodcommercems.service.impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
@Validated
public class AuthenticationController {
    private final RegisterService registerService;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid CustomerRequest request) {
        return registerService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }
    @GetMapping(path = "/login")
    public LoginResponse login(@RequestBody AuthenticationRequest request) {
        return userDetailsService.loadUserByUsernameAndPassword(request.getUsername(), request.getPassword());
    }

}
