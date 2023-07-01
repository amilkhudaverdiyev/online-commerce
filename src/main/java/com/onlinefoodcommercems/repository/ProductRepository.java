package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select * from products where is_activated = true", nativeQuery = true)
    List<Product> findAllByActivated();
}
