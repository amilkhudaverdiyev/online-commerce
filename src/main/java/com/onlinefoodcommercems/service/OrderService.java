package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.response.OrderResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();

    List<OrderResponse> findAllOrdersByCustomer(Long id);

    void deleteById(Long id);

    void save(Long id) throws MessagingException;

}
