package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.Responses;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CategoryMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse save(CategoryRequest category) {
        var categoryEntity = categoryMapper.fromDTO(category);
        var createdCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDTO(createdCategory);
    }

    @Override
    public void update(Long id, CategoryRequest categoryRequest) {
        var category=categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(Responses.CATEGORY_NOT_FOUND));
        categoryMapper.toDTOmap(category,categoryRequest);
        categoryRepository.save(category);

    }

    @Override
    public List<CategoryDto> findAllByActivated() {
        var active = categoryRepository.findAllByActivated();
        return categoryMapper.toDTOList(active);
    }

    @Override
    public List<CategoryResponse> findAll() {
        var category = categoryRepository.findAll();
        return categoryMapper.toDTOs(category);
    }

    @Override
    public CategoryDto findById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(Responses.CATEGORY_NOT_FOUND));
        return categoryMapper.toDTOId(category);
    }

    @Override
    public void deleteById(Long id) {
        var category = categoryRepository.getById(id);
        category.setStatus(Status.DEACTIVE);
        categoryRepository.save(category);
        List<Product> deletedProducts = productRepository.findProductStatusInActiveByCategoryId(category.getId());
        deletedProducts.forEach((product) ->
                product.setStatus(Status.DEACTIVE));
        productRepository.saveAll(deletedProducts);
    }

    @Override
    public void enableById(Long id) {
        var category = categoryRepository.getById(id);
        category.setStatus(Status.ACTIVE);
        categoryRepository.save(category);
        List<Product> deletedProducts = productRepository.findProductStatusInDeactiveByCategoryId(category.getId());
        deletedProducts.forEach((product) -> {
            product.setStatus(Status.ACTIVE);
        });
        productRepository.saveAll(deletedProducts);
    }

    @Override
    public String getAllCategoryCount() {
        return Responses.ACTIVE_COUNT + getActiveCount() + "\n"
                + Responses.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + Responses.ALL_COUNT + getCount();
    }

    private int getActiveCount() {
        return categoryRepository.countActiveAllBy();
    }

    private int getDeactiveCount() {
        return categoryRepository.countDeactiveAllBy();
    }

    private int getCount() {
        return categoryRepository.countAllBy();
    }


}
