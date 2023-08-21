package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
