package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/count")
    public String getAllCustomerCount() {
        return customerService.getAllCustomerCount();
    }

    @GetMapping("/all-active")
    public List<CustomerResponse> getAllCustomerActivated() {
        return customerService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest request) {
        customerService.save(request);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,
                                                 @RequestBody CustomerRequest request) {
        customerService.update(id, request);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledCustomer(@PathVariable Long id) {
        customerService.enableById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}