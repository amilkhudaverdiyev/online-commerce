package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.ProductMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new NotDataFound(Messages.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDto> findAllByActivated() {
        var active = productRepository.findAllByActivated();
        return productMapper.toDTOList(active);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        var product = productRepository.getById(id);
        product.setStatus(Status.DEACTIVE);
        productRepository.save(product);

    }

    @Override
    public void enableById(Long id) {
        var product = productRepository.getById(id);
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);
    }

    public List<ProductDto> findByCategoryId(Long id) {
        id = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(Messages.CATEGORY_NOT_FOUND)).getId();
        var product = productRepository.findProductStatusInActiveByCategoryId(id);
        return productMapper.toDTOList(product);

    }

    public List<ProductDto> searchProducts(String keyword) {
        var product = productRepository.searchProducts(keyword);
        return productMapper.toDTOList(product);
    }


}