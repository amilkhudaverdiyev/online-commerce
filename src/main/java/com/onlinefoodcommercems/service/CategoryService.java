package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;


import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryResponse save(CategoryRequest category);

    CategoryResponse update(CategoryRequest category);

    List<CategoryDto> findAllByActivated();

    List<CategoryDto> findALl();
    CategoryDto findById(Long id);
    void deleteById(Long id);

    void enableById(Long id);

//    List<CategoryDto> getCategoriesAndSize();
}
