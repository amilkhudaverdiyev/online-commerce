package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.request.PasswordResetRequest;
import com.onlinefoodcommercems.dto.request.PasswordSetRequest;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Validated
public class AuthenticationController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseDetail register(@RequestBody @Valid CustomerRequest request) {
        registerService.register(request);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }

    @GetMapping(path = "/login")
    public ResponseDetail authenticate(@RequestBody @Valid AuthenticationRequest request) {
        registerService.login(request);
        return ResponseDetail.builder()
                .message(ResponseMessage.LOGIN_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PutMapping("/change-password")
    public ResponseDetail changePassword(@AuthenticationPrincipal Customer customer,
                                                 @Valid @RequestBody PasswordResetRequest passwordReset
                                                 ) {
            registerService.changePassword(customer.getUsername(), passwordReset);
        return ResponseDetail.builder()
                .message(ResponseMessage.PASSWORD_SUCCESSFULLY_CHANGED)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();

    }

    @PutMapping("/forgot-password")
    public ResponseDetail forgotPassword(@RequestParam String username) {
         registerService.forgotPassword(username);
        return ResponseDetail.builder()
                .message(ResponseMessage.MESSAGE_SEND_SUCCESFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();
    }

    @PutMapping("/set-password")
    public ResponseDetail setPassword(@RequestBody @Valid PasswordSetRequest request) {
        registerService.setPassword(request.getEmail(), request.getOtp(), request.getPassword());
        return ResponseDetail.builder()
                .message(ResponseMessage.PASSWORD_SUCCESSFULLY_CHANGED)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();

    }
}

