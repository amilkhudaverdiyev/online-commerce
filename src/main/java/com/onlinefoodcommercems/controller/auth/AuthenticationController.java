package com.onlinefoodcommercems.controller.auth;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.request.PasswordResetRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.exception.InputFieldException;
import com.onlinefoodcommercems.service.RegisterService;
import com.onlinefoodcommercems.utils.MessageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Validated
public class AuthenticationController {
    private final RegisterService registerService;

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

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal Customer customer,
                                                 @Valid @RequestBody PasswordResetRequest passwordReset,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(registerService.changePassword(customer.getUsername(), passwordReset));
        }
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> changePassword(@RequestParam String username) {
        return new ResponseEntity<>(registerService.forgotPassword(username), HttpStatus.OK);
    }

    @PutMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String username,
                                              @RequestParam Integer code,
                                              @RequestParam String newPassword) {
        return new ResponseEntity<>(registerService.setPassword(username, code, newPassword), HttpStatus.OK);

    }
}

