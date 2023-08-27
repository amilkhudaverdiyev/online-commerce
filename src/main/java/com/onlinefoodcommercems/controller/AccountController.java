package com.onlinefoodcommercems.controller;


import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.AccountRequest;
import com.onlinefoodcommercems.dto.response.AccountResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.dto.update.AccountUpdateRequest;
import com.onlinefoodcommercems.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail createAccount(@RequestBody AccountRequest account) {
        log.error("account  {}", account);
        accountService.createAccount(account);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail updateAccount(Principal principal,
                                        @ModelAttribute @Valid AccountUpdateRequest request) {
        log.error("account updated {}", request);
        log.error("customer {}", principal);
        accountService.update(principal.getName(), request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}