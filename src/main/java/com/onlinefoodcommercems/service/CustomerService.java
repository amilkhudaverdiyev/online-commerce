package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.entity.Customer;

import java.util.List;

public interface CustomerService {



    void update(Long id, CustomerRequest customerRequest);

    List<CustomerResponse> findAllByActivated();

    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    CustomerResponse findByUsername(String username);


    void deleteById(Long id);

    void enableById(Long id);

    String getAllCustomerCount();
}
