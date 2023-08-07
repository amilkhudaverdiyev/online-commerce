package com.onlinefoodcommercems.controller.auth;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;
import com.onlinefoodcommercems.service.RegisterService;
import com.onlinefoodcommercems.service.impl.UserDetailsServiceImpl;
import com.onlinefoodcommercems.utils.MessageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@RequestBody @Valid CustomerRequest request) {
        registerService.register(request);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(registerService.login(request));
    }
}
