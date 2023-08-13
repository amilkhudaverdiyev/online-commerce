package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.exceptions.GenericException;
import com.onlinefoodcommercems.mapper.ProductMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        var productPage = productRepository.findAllPagableData(pageable);
        List<ProductDto> productResponses = productMapper.toDTOList(productPage.getContent());
        return new PageImpl<>(productResponses, pageable, productPage.getTotalElements());
    }


    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setStatus(Status.DEACTIVE);
        }
        var product = productMapper.fromDTO(productRequest);
        var category = categoryRepository.findByIdAndActivated(productRequest.getCategoryId())
                .orElseThrow(() -> new GenericException(ResponseMessage.CATEGORY_NOT_FOUND,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()));
        product.setCategory(category);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        var product = productRepository.findById(id).orElseThrow(()
                -> new GenericException(ResponseMessage.PRODUCT_NOT_FOUND,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()));
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setStatus(Status.DEACTIVE);
        }
        productMapper.toDTOmap(product, productRequest);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        var product = productRepository.findProductStatusActivity(id).orElseThrow(()
                -> new GenericException(ResponseMessage.PRODUCT_NOT_FOUND,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()));
        product.setStatus(Status.DEACTIVE);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {
        var product = productRepository.findProductStatusDeactivity(id).orElseThrow(()
                -> new GenericException(ResponseMessage.PRODUCT_NOT_FOUND, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()));
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);
    }

    @Override
    public void increaseAllPrice(Double percent) {
        try {
            productRepository.increaseAll(percent);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void decreaseAllPrice(Double percent) {
        productRepository.decreaseAll(percent);
    }

    public List<ItemResponse> findByCategoryId(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.CATEGORY_NOT_FOUND)).getId();
        var product = productRepository.findProductStatusInActiveByCategoryId(category);
        return productMapper.toDTOs(product);

    }

    public List<ProductDto> searchProducts(String keyword) {
        var product = productRepository.searchProducts(keyword);
        return productMapper.toDTOList(product);
    }


}