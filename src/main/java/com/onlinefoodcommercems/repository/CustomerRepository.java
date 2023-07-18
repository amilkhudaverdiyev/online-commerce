package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
