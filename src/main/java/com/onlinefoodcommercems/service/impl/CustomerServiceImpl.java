package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.update.CustomerUpdateReqeust;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CustomerMapper;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;


    @Override
    public void update(String username, CustomerUpdateReqeust customerRequest) {
        log.error("customerRequest {}", customerRequest);
        var customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        customerMapper.toDTOmap(customer, customerRequest);
        log.error("customer {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllByActivated() {
        var activeCustomer = customerRepository.findAllByActivated(true, Roles.USER);
        log.error("activeCustomer {}", activeCustomer);
        return customerMapper.toDTOs(activeCustomer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        var user = customerRepository.findAllByUser(Roles.USER);
        log.error("user {}", user);
        return customerMapper.toDTOs(user);
    }

    @Override
    public CustomerResponse findById(Long id) {
        log.error("id {}", id);
        var customer = customerRepository.findByIdAndAuthority(id).
                orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        log.error("customer {}", customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponse findByUsername(String username) {
        log.error("username {}", username);
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(ResponseMessage.ALL_COUNT));
        log.error("customer {}", customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public void deleteById(Long id) {
        log.error("id {}", id);
        var customer = customerRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        customer.setEnabled(false);
        log.error("customer {}", customer);
        customerRepository.save(customer);

    }

    @Override
    public void enableById(Long id) {
        log.error("id {}", id);
        var customer = customerRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        customer.setEnabled(true);
        log.error("customer {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public String getAllCustomerCount() {
        return ResponseMessage.ACTIVE_COUNT + getActiveCount() + "\n"
                + ResponseMessage.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + ResponseMessage.ALL_COUNT + getCount();
    }

    private int getActiveCount() {
        return customerRepository.countAllBy(true, Roles.USER);
    }

    private int getDeactiveCount() {
        return customerRepository.countAllBy(false, Roles.USER);
    }

    private int getCount() {
        return customerRepository.countAll(Roles.USER);
    }

    @Override
    public List<Customer> getCustomerByEnabledAdmin() {
        return customerRepository.findByAccountStatusAndAuthorities(Status.DEACTIVE, Roles.ADMIN);
    }

    @Override
    public List<Customer> getCustomerByEnabledUser() {
        return customerRepository.findByAccountStatusAndAuthorities(Status.DEACTIVE, Roles.USER);
    }

    @Override
    public void updateCustomerStatus(Customer customer) {
        log.error("customer {}", customer);
        customer.setAccountStatus(Status.ACTIVE);
        log.error("customer {}", customer);
        customerRepository.save(customer);

    }
}
