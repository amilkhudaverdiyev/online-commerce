package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select * from categories where status = 'ACTIVE'", nativeQuery = true)
    List<Category> findAllByActivated();

    @Query(value="select * from categories where status = 'ACTIVE' and category_id=:id",nativeQuery = true)
    Optional<Category> findByIdAndActivated(Long id);

    List<Category> findByStatus(Status status);
}
