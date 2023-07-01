package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CategoryMapper;
import com.onlinefoodcommercems.mapper.ProductMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setActivated(false);
            productRequest.setDeleted(true);
        }
        var product = productMapper.fromDTO(productRequest);

        var category = categoryRepository.findByIdAndActivated(productRequest.getCategoryId())
                .orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));
        product.setCategory(category);
        return productMapper.toDTO(productRepository.save(product));

    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<ProductDto> findAllByActivated() {
        var active= productRepository.findAllByActivated();
        return productMapper.toDTOList(active);
    }

    @Override
    public List<ProductDto> findALl() {
        return null;
    }

    @Override
    public ProductDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void enableById(Long id) {

    }
}
//    @Override
//    public ProductResponse save(ProductRequest productRequest, Long categoryId) {
//        if (productRequest.getCurrentQuantity() == 0) {
//            productRequest.setActivated(false);
//            productRequest.setDeleted(true);
//        }
//        var category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotDataFound("Not Category"));
//        var product = productMapper.fromDTO(productRequest);
//        product.setCategory(category);
//        var createdProduct = productRepository.save(product);
//        return productMapper.toDTO(createdProduct);
//    }