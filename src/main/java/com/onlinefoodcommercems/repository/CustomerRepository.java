package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Customer;
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
    
    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.enabled=true and r.authority='USER'")
    List<Customer> findAllByActivated();
    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.id=:id and r.authority='USER'")
    Optional<Customer> findById(Long id);
    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where  r.authority='USER'")
    List<Customer> findAll();


    @Query(value = "SELECT count(c)\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.enabled=true and r.authority='USER'")
    int countActiveAllBy();
    @Query(value = "SELECT count(c)\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.enabled=false and r.authority='USER'")
    int countDeactiveAllBy();
    @Query(value = "SELECT count(c)\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where r.authority='USER'")
    int countAllBy();


}
