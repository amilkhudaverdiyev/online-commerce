package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
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
    public List<CustomerResponse> getAllCategory() {
        return customerService.findAll();
    }

    @GetMapping("/count")
    public String getAllCategoryCount() {
        return customerService.getAllCustomerCount();
    }

    @GetMapping("/all-active")
    public List<CustomerResponse> getAllCategoryActivated() {
        return customerService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CustomerRequest request) {
        customerService.save(request);
        return MessageUtils.getResponseEntity(Messages.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,
                                                 @RequestBody CustomerRequest request) {
        customerService.update(id, request);
        return MessageUtils.getResponseEntity(Messages.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return MessageUtils.getResponseEntity(Messages.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledustomer(@PathVariable Long id) {
        customerService.enableById(id);
        return MessageUtils.getResponseEntity(Messages.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}