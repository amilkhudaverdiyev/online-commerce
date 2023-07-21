package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products where status = 'ACTIVE'", nativeQuery = true)
    List<Product> findAllByActivated();

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



}
