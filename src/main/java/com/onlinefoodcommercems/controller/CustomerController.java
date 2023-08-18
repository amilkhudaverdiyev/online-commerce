package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.dto.update.CustomerUpdateReqeust;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail updateCustomer(@AuthenticationPrincipal Customer customer,
                                                 @RequestBody CustomerUpdateReqeust request) {
        customerService.update(customer.getUsername(), request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();
    }

}