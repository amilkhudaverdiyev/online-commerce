package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
