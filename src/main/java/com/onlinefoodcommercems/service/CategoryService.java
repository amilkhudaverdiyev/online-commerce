package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;


import java.util.List;

public interface CategoryService {
    CategoryResponse save(CategoryRequest category);

    void update(Long id, CategoryRequest categoryRequest);

    List<CategoryDto> findAllByActivated();

    List<CategoryResponse> findAll();
    CategoryDto findById(Long id);
    void deleteById(Long id);

    void enableById(Long id);

    String getAllCategoryCount();
}
