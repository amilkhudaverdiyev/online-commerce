package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "select * from orders where customer_id=:id and accept_status='LOADING'", nativeQuery = true)
    List<Order> findCustomerInOrder(Long id);
    @Query(value = "select * from orders where customer_id=:id and accept_status='ACCEPT'", nativeQuery = true)
    List<Order> findAcceptStatus(Long id);
}
