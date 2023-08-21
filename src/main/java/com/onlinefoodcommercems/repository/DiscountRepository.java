package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    List<Discount> findByStatus(DiscountStatus status);
//    @Query(value = "select * from discount where status = 'ACTIVE'", nativeQuery = true)
//    List<Discount> findByActiveStatus();
//    @Query(value = "select * from discount where status = 'DEACTIVE'", nativeQuery = true)
//    List<Discount> findByDeactiveStatus();
//    @Query(value = "select * from discount where status = 'LOADING'", nativeQuery = true)
//    List<Discount> findByLoadingStatus();
//    @Query(value = "select * from discount where id=:id and status = 'ACTIVE' ",nativeQuery = true)
//    Optional<Discount> findDiscountStatusActivity(Long id);
//    @Query(value = "select * from discount where product_id=:id",nativeQuery = true)
//    Optional<Discount> findDiscountProductStatusActivity(Long id);






}
