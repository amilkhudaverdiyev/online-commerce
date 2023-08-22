package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
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
        log.error("category {}",category);
        var categoryEntity = categoryMapper.fromDTO(category);
        var createdCategory = categoryRepository.save(categoryEntity);
        log.error("createdCategory {}",createdCategory);
        return categoryMapper.toDTO(createdCategory);
    }

    @Override
    public void update(Long id, CategoryRequest categoryRequest) {
        log.error("categoryRequest {}",categoryRequest);
        var category=categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CATEGORY_NOT_FOUND));
        categoryMapper.toDTOmap(category,categoryRequest);
        log.error("category {}",category);
        categoryRepository.save(category);

    }

    @Override
    public List<CategoryDto> findAllByActivated() {
        var activeCategory = categoryRepository.findAllByActivated();
        log.error("active {}",activeCategory);
        return categoryMapper.toDTOList(activeCategory);
    }

    @Override
    public List<CategoryResponse> findAll() {
        var category = categoryRepository.findAll();
        log.error("category {}",category);
        return categoryMapper.toDTOs(category);
    }

    @Override
    public CategoryDto findById(Long id) {
        log.error("id {}",id);
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CATEGORY_NOT_FOUND));
        log.error("category {}",category);
        return categoryMapper.toDTOId(category);
    }

    @Override
    public void deleteById(Long id) {
        log.error("id {}",id);
        var category = categoryRepository.getById(id);
        category.setStatus(Status.DEACTIVE);
        log.error("category {}",category);
        categoryRepository.save(category);
        List<Product> deletedProducts = productRepository.findProductStatusInActiveByCategoryId(category.getId());
        deletedProducts.forEach((product) ->
                product.setStatus(Status.DEACTIVE));
        log.error("deletedProducts {}",deletedProducts);
        productRepository.saveAll(deletedProducts);
    }

    @Override
    public void enableById(Long id) {
        log.error("id {}",id);
        var category = categoryRepository.getById(id);
        category.setStatus(Status.ACTIVE);
        log.error("category {}",category);
        categoryRepository.save(category);
        List<Product> enabledProducts = productRepository.findProductStatusInDeactiveByCategoryId(category.getId());
        enabledProducts.forEach((product) -> {
            product.setStatus(Status.ACTIVE);
        });
        log.error("enabledProducts {}",enabledProducts);
        productRepository.saveAll(enabledProducts);
    }

    @Override
    public String getAllCategoryCount() {
        return ResponseMessage.ACTIVE_COUNT + getActiveCount() + "\n"
                + ResponseMessage.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + ResponseMessage.ALL_COUNT + getCount();
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
