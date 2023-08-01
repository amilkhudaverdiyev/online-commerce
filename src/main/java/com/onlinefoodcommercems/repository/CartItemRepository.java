package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(value = "select * from cart_item where cart_id=:id and customer_id=:ids", nativeQuery = true)
    Optional<CartItem> findByIdAndActivated(Long id, Long ids);

    @Query(value = "select * from cart_item where customer_id=:id", nativeQuery = true)
    Optional<CartItem> findByCustomerId(Long id);

//    List<CartItem> findByStatus(CartStatus status);
//
    @Transactional
    @Modifying
    @Query(value = "delete from cart_item where customer_id=:id", nativeQuery = true)
    void deleteCartItemByCustomer(Long id);
}
