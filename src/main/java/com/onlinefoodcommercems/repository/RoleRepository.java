package com.onlinefoodcommercems.repository;


import com.onlinefoodcommercems.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserAuthority,Long> {

}
