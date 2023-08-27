package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import jakarta.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();

    List<OrderResponse> findOrderByStatus(String status);

    List<OrderResponse> findAllOrdersByCustomer(String username);

    List<Order> findByStatus();

    List<Order> findByStatuss(OrderStatus status);

    void updateOrderStatus(Order order);

    void deleteById(Long id);

    void save(String username, LocalDateTime deliveryDate) throws MessagingException;

}
