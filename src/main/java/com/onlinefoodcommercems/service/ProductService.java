package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest customerRequest);

    ProductResponse update(ProductRequest productRequest);

    List<ProductDto> findAllByActivated();

    public void deleteById(Long id);

    public void enableById(Long id);

    List<ProductDto> findByCategoryId(Long id);

    List<ProductDto> searchProducts(String keyword);

    //    List<ProductDto> findAllPagableData(Pageable pageable);
    Page<ProductDto> getAllProducts(Pageable pageable);

}
