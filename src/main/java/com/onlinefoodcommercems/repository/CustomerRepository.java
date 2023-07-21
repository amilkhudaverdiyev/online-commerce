package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUsername(String username);
    @Query(value = "select * from customer where status = 'ACTIVE'", nativeQuery = true)
    List<Customer> findAllByActivated();


    @Query(value = "select count(*)from customer where status = 'ACTIVE'", nativeQuery = true)
    int countActiveAllBy();

    @Query(value = "select count(*)from customer where status = 'DEACTIVE'", nativeQuery = true)
    int countDeactiveAllBy();

    @Query(value = "select count(*)from customer", nativeQuery = true)
    int countAllBy();

}
