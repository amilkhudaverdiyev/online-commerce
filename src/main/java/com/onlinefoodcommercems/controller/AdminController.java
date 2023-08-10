package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.service.RegisterService;
import com.onlinefoodcommercems.utils.MessageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Validated
public class AdminController {
    private final RegisterService registerService;
    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid CustomerRequest request) {
        registerService.registerAdmin(request);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerResponse> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllCustomerCount() {
        return customerService.getAllCustomerCount();
    }

    @GetMapping("/all-active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerResponse> getAllCustomerActivated() {
        return customerService.findAllByActivated();
    }

    @RequestMapping(value = "/id/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletedCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> enabledCustomer(@PathVariable Long id) {
        customerService.enableById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }
}
