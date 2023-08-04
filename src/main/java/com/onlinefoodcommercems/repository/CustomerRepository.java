package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.entity.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @EntityGraph(attributePaths = {"authorities"})
    Optional<Customer> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Customer a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableUser(String username);

    //Optional<Customer> findByUsername(String username);
    @Query(value = "select * from customer where status = 'ACTIVE'", nativeQuery = true)
    List<Customer> findAllByActivated();


    @Query(value = "select count(*)from customer where status = 'ACTIVE'", nativeQuery = true)
    int countActiveAllBy();

    @Query(value = "select count(*)from customer where status = 'DEACTIVE'", nativeQuery = true)
    int countDeactiveAllBy();

    @Query(value = "select count(*)from customer", nativeQuery = true)
    int countAllBy();

}
