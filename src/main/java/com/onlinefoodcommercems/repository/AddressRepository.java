package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(value = "select * from address where status = 'ACTIVE'", nativeQuery = true)
    List<Address> findAllByActivated();


}
