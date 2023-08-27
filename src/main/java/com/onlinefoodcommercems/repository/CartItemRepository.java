package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    @Modifying
    @Query(value = "delete from cart_item where customer_id=:id", nativeQuery = true)
    void deleteCartItemByCustomer(Long id);
}
