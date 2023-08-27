package com.onlinefoodcommercems.controller;


import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.service.TransferService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;


@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseDetail transfer(Principal principal,
                                   @RequestParam String recieveAccount,
                                   @RequestParam
                                   @Positive(message = ResponseMessage.PAYMENT_VALID)
                                   BigDecimal payment) {
        transferService.transfer(principal.getName(), recieveAccount, payment);
        return ResponseDetail.builder()
                .message(ResponseMessage.AMOUNT_SEND_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();

    }
}
