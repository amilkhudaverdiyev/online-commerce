package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse save(CustomerRequest customerRequest);


    void update(Long id, CustomerRequest customerRequest);

    List<CustomerResponse> findAllByActivated();

    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    CustomerResponse findByUsername(String username);

    void deleteById(Long id);

    void enableById(Long id);

    String getAllCustomerCount();
}
