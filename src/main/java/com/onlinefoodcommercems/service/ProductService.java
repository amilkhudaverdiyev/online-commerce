package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.entity.Product;

import java.util.List;

public interface ProductService {
    //ProductResponse save(ProductRequest productRequest, Long categoryId);
    ProductResponse createProduct(ProductRequest customerRequest) ;

    ProductResponse update(ProductRequest productRequest);

    List<ProductDto> findAllByActivated();

    List<ProductDto> findALl();
    ProductDto findById(Long id);
    void deleteById(Long id);

    void enableById(Long id);
}
