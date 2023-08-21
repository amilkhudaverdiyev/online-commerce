package com.onlinefoodcommercems.controller;


import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class TransferController {
    private final TransferService transferService;
    @PostMapping
    public ResponseDetail transfer(@AuthenticationPrincipal Customer customer,
                                   @RequestParam String recieveAccount,
                                   @RequestParam BigDecimal payment) {
        transferService.transfer(customer.getUsername(),recieveAccount,payment);
        return ResponseDetail.builder()
                .message(ResponseMessage.AMOUNT_SEND_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();

    }
}
