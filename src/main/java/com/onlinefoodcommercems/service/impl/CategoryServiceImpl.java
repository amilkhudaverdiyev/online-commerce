package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CategoryMapper;
import com.onlinefoodcommercems.mapper.ProductMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse save(CategoryRequest category) {
        var created = categoryMapper.fromDTO(category);
        var createdCategory= categoryRepository.save(created);
        return categoryMapper.toDTO(createdCategory);
    }

    @Override
    public CategoryResponse update(CategoryRequest category) {
        return null;
    }

    @Override
    public List<CategoryDto> findAllByActivated() {
        var active= categoryRepository.findAllByActivated();
        return categoryMapper.toDTOList(active);
    }

    @Override
    public List<CategoryDto> findALl() {
        var category = categoryRepository.findAll();
        return categoryMapper.toDTOList(category);
    }

    @Override
    public CategoryDto findById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));
        return categoryMapper.toDTOId(category);
    }

    @Override
    public void deleteById(Long id) {
        var category = categoryRepository.getById(id);
        category.setStatus(Status.DEACTIVE);
        categoryRepository.save(category);
        List<Product> deletedProducts=productRepository.findProductStatusInActiveByCategoryId(category.getId());
        deletedProducts.forEach((product) ->
            product.setStatus(Status.DEACTIVE));
            productRepository.saveAll(deletedProducts);
    }

    @Override
    public void enableById(Long id) {
        var category = categoryRepository.getById(id);
        category.setStatus(Status.ACTIVE);
        categoryRepository.save(category);
        List<Product> deletedProducts=productRepository.findProductStatusInDeactiveByCategoryId(category.getId());
        deletedProducts.forEach((product) ->{product.setStatus(Status.ACTIVE); });
        productRepository.saveAll(deletedProducts);
    }

}
