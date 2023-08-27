package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.update.CustomerUpdateReqeust;
import com.onlinefoodcommercems.entity.Customer;

import java.util.List;

public interface CustomerService {

    void update(String username, CustomerUpdateReqeust customerRequest);

    List<CustomerResponse> findAllByActivated();

    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    CustomerResponse findByUsername(String username);

    void deleteById(Long id);

    void enableById(Long id);

    String getAllCustomerCount();

    List<Customer> getCustomerByEnabledAdmin();

    List<Customer> getCustomerByEnabledUser();

    void updateCustomerStatus(Customer customer);
}
