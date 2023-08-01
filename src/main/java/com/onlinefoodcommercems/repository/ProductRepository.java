package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from products where products.category_id=:id and status = 'ACTIVE' ", nativeQuery = true)
    List<Product> findProductStatusInActiveByCategoryId(Long id);

    @Query(value = "select * from products where product_id=:id and status = 'ACTIVE' ", nativeQuery = true)
    Optional<Product> findProductStatusActivity(Long id);

    @Query(value = "select * from products where products.category_id=:id and status = 'DEACTIVE' ", nativeQuery = true)
    List<Product> findProductStatusInDeactiveByCategoryId(Long id);

    @Query("SELECT u FROM Product u WHERE lower( u.name) LIKE %?1%")
    List<Product> searchProducts(String keyword);

    @Query(value = "select * from products where status = 'ACTIVE'", nativeQuery = true)
    Page<Product> findAllPagableData(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update products set unit_price=unit_price+(unit_price*:percent/100) where  product_id in (select product_id from products) and status='ACTIVE'", nativeQuery = true)
    void increaseAll(Double percent);

    @Modifying
    @Transactional
    @Query(value = "update products set unit_price=unit_price-(unit_price*:percent/100) where  product_id in (select product_id from products) and status='ACTIVE'", nativeQuery = true)
    void decreaseAll(Double percent);

}
