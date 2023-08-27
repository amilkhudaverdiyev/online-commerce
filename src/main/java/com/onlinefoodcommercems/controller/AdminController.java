package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final RegisterService registerService;
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseDetail registerAdmin(@RequestBody @Valid CustomerRequest request) {
        registerService.registerAdmin(request);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerResponse> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/customer/count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllCustomerCount() {
        return customerService.getAllCustomerCount();
    }

    @GetMapping("/customer/all-active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerResponse> getAllCustomerActivated() {
        return customerService.findAllByActivated();
    }

    @GetMapping(value = "/customer/find/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @DeleteMapping(value = "/customer/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail deletedCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value()).build();
    }

    @PutMapping(value = "/customer/enable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail enabledCustomer(@PathVariable Long id) {
        customerService.enableById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();
    }
}
