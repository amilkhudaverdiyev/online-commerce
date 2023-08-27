package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders where customer_id=:id and status='LOADING'", nativeQuery = true)
    List<Order> findCustomerInOrder(Long id);

    @Query(value = "select * from orders where status=:status", nativeQuery = true)
    List<Order> findOrderByStatus(String status);

    List<Order> findOrdersByStatus(OrderStatus status);
}
