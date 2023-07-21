package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.AddressMapper;
import com.onlinefoodcommercems.mapper.CustomerMapper;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        var customer = customerMapper.fromDTO(customerRequest);
        var address = addressMapper.fromDTO(customerRequest.getAddress());
        customer.setAddress(address);
        var createdCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(createdCustomer);
    }


    @Override
    public void update(Long id, CustomerRequest customerRequest) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new NotDataFound(Messages.CUSTOMER_NOT_FOUND));
        customerMapper.toDTOmap(customer, customerRequest);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllByActivated() {
        var active = customerRepository.findAllByActivated();
        return customerMapper.toDTOs(active);
    }

    @Override
    public List<CustomerResponse> findAll() {
        var active = customerRepository.findAll();
        return customerMapper.toDTOs(active);
    }

    @Override
    public CustomerResponse findById(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new NotDataFound(Messages.CUSTOMER_NOT_FOUND));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponse findByUsername(String username) {
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(Messages.CUSTOMER_NOT_FOUND));
        return customerMapper.toDTO(customer);
    }

    @Override
    public void deleteById(Long id) {
        var customer = customerRepository.getById(id);
        customer.setStatus(Status.DEACTIVE);
        customerRepository.save(customer);

    }

    @Override
    public void enableById(Long id) {
        var customer = customerRepository.getById(id);
        customer.setStatus(Status.ACTIVE);
        customerRepository.save(customer);
    }

    @Override
    public String getAllCustomerCount() {
        return Messages.ACTIVE_COUNT + getActiveCount() + "\n"
                + Messages.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + Messages.ALL_COUNT + getCount();
    }

    private int getActiveCount() {
        return customerRepository.countActiveAllBy();
    }

    private int getDeactiveCount() {
        return customerRepository.countDeactiveAllBy();
    }

    private int getCount() {
        return customerRepository.countAllBy();
    }
}
