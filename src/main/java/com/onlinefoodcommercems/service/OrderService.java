package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.enums.OrderStatus;
import jakarta.mail.MessagingException;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();
    List<OrderResponse> findOrderByStatus(String status);

    List<OrderResponse> findAllOrdersByCustomer(Long id);

    void deleteById(Long id);

    void save(Long id) throws MessagingException;

}