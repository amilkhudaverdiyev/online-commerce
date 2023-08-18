package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    String getAllProductCount();
    ProductResponse createProduct(ProductRequest productRequest);

    void update(Long id, ProductRequest productRequest);

    Page<ProductDto> getAllProducts(Pageable pageable);

    void deleteById(Long id);

    void enableById(Long id);

    void increaseAllPrice(Double percent);

    void decreaseAllPrice(Double percent);

    List<ItemResponse> findByCategoryId(Long id);

    List<ProductDto> searchProducts(String keyword);


}
