package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query(value = "select * from cart_item where cart_id=:id and customer_id=:ids", nativeQuery = true)
    Optional<CartItem> findByIdAndActivated(Long id, Long ids);
}
