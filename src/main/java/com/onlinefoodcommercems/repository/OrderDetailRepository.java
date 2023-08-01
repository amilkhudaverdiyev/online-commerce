package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
