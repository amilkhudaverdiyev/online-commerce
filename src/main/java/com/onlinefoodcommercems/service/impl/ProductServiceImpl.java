package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.ItemResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        log.error("product {}" + pageable);
        var productPage = productRepository.findAllPagableData(pageable);
        var productResponses = productMapper.toDTOList(productPage.getContent());
        log.error("product {}" + productResponses);
        return new PageImpl<>(productResponses, pageable, productPage.getTotalElements());
    }

    @Override
    public List<ProductResponse> findAll() {
        var product = productRepository.findAll();
        log.error("product {}" + product);
        return productMapper.toDTOs(product);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        log.error("productRequest {}" + productRequest);
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setStatus(Status.DEACTIVE);
        }
        var product = productMapper.fromDTO(productRequest);
        log.error("productRequest {}" + product);
        var category = categoryRepository.findByIdAndActivated(productRequest.getCategoryId())
                .orElseThrow(()
                        -> new NotDataFound(ResponseMessage.CATEGORY_NOT_FOUND));
        log.error("productRequest {}" + category);
        product.setCategory(category);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        var product = productRepository.findById(id).orElseThrow(()
                -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setStatus(Status.DEACTIVE);
        }
        productMapper.toDTOmap(product, productRequest);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        var product = productRepository.findProductStatusActivity(id).orElseThrow(()
                -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        product.setStatus(Status.DEACTIVE);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {
        var product = productRepository.findProductStatusDeactivity(id).orElseThrow(()
                -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);
    }

    @Override
    public void increaseAllPrice(Double percent) {
        productRepository.increaseAll(percent);
    }

    @Override
    public void decreaseAllPrice(Double percent) {
        productRepository.decreaseAll(percent);
    }

    public List<ItemResponse> findByCategoryId(Long id) {
        log.error("id" + id);
        var category = categoryRepository.findById(id).orElseThrow(()
                -> new NotDataFound(ResponseMessage.CATEGORY_NOT_FOUND));
        log.error("category " + category);
        var product = productRepository.findProductStatusInActiveByCategoryId(category.getId());
        log.error("product " + product);
        return productMapper.productToItemResponse(product);
    }

    public List<ProductDto> searchProducts(String keyword) {
        log.error("keyword" + keyword);
        var product = productRepository.searchProducts(keyword);
        log.error("product" + product);
        return productMapper.toDTOList(product);
    }

    @Override
    public String getAllProductCount() {
        return ResponseMessage.ACTIVE_COUNT + getActiveCount() + "\n"
                + ResponseMessage.DEACTIVE_COUNT + getDeactiveCount() + "\n"
                + ResponseMessage.ALL_COUNT + getCount();
    }

    private int getActiveCount() {
        return productRepository.countActiveAllBy();
    }

    private int getDeactiveCount() {
        return productRepository.countDeactiveAllBy();
    }

    private int getCount() {
        return productRepository.countAllBy();
    }
}