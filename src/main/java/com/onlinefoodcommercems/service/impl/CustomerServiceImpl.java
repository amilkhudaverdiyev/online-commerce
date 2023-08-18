package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.update.CustomerUpdateReqeust;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
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
    private final CustomerRepository customerRepository;


    @Override
    public void update(String username, CustomerUpdateReqeust customerRequest) {
        var customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
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
        var customer = customerRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponse findByUsername(String username) {
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(ResponseMessage.ALL_COUNT));
        return customerMapper.toDTO(customer);
    }

    @Override
    public void deleteById(Long id) {
        var customer = customerRepository.getById(id);
        customer.setEnabled(false);
        customerRepository.save(customer);

    }

    @Override
    public void enableById(Long id) {
        var customer = customerRepository.getById(id);
        customer.setEnabled(true);
        customerRepository.save(customer);
    }

    @Override
    public String getAllCustomerCount() {
        return ResponseMessage.ACTIVE_COUNT + getActiveCount() + "\n"
                + ResponseMessage.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + ResponseMessage.ALL_COUNT + getCount();
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
