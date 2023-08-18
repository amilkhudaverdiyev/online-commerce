package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByStatus(Status status);


}
