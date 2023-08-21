package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
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
            "                   ON c.id = r.customer.id where c.enabled=:enabled and r.authority=:role")
    List<Customer> findAllByActivated(Boolean enabled,Roles role);
    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.id=:id and r.authority=:role")
    Optional<Customer> findByIdAndAuthority(Long id,Roles role);
    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where  r.authority=:role")
    List<Customer> findAllByUser(Roles role);

    @Query(value = "SELECT count(c)\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.enabled=:enabled and r.authority=:role")
    int countAllBy(Boolean enabled,Roles role);
    @Query(value = "SELECT count(c)\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where r.authority=:role")
    int countAll(Roles role);

    @Query(value = "SELECT c\n" +
            "FROM Customer c\n" +
            "         LEFT JOIN UserAuthority r\n" +
            "                   ON c.id = r.customer.id where c.accountStatus=:status and r.authority=:role")
    List<Customer> findByAccountStatusAndAuthorities(Status status, Roles role);


}
