package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT a\n, c\n" +
            "FROM Address a\n" +
            "          JOIN Customer c\n" +
            "                   ON c.id = a.customer.id LEFT JOIN UserAuthority r\n" +
            " ON c.id=r.customer.id where r.authority='USER'")
    List<Address> findByStatus(Status status);

    @Query(value = "SELECT a\n, c\n" +
            "FROM Address a\n" +
            "          JOIN Customer c\n" +
            "                   ON c.id = a.customer.id LEFT JOIN UserAuthority r\n" +
            " ON c.id=r.customer.id where r.authority='USER'")
    List<Address> findAllByUser();


}
