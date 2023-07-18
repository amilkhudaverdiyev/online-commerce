package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CategoryMapper;
import com.onlinefoodcommercems.mapper.DiscountMapper;
import com.onlinefoodcommercems.mapper.ProductMapper;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final DiscountRepository discountRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
//    var unitPrice = productRequest.getUnitPrice();
//    var discount = productRequest.getDiscountRequest().getDiscount();
//    var costPrice = unitPrice - unitPrice * discount / 100;
//    productRequest.setCostPrice(costPrice);
        if (productRequest.getCurrentQuantity() == 0) {
            productRequest.setStatus(Status.DEACTIVE);
        }
        var product = productMapper.fromDTO(productRequest);
        var category = categoryRepository.findByIdAndActivated(productRequest.getCategoryId())
                .orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));
        product.setCategory(category);
//        var discounts = discountRepository.findById(productRequest.getDiscountRequest().getId())
//                .orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));
//        product.setDiscount(discounts);
        return productMapper.toDTO(productRepository.save(product));
    }


    @Override
    public ProductResponse update(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<ProductDto> findAllByActivated() {
        var active = productRepository.findAllByActivated();
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
        var product = productRepository.getById(id);
        product.setStatus(Status.DEACTIVE);
        productRepository.save(product);

    }

    @Override
    public void enableById(Long id) {

    }

    public List<ProductDto> findByCategoryId(Long id) {
        id = categoryRepository.findById(id).orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY)).getId();
        var product = productRepository.findProductStatusInActiveByCategoryId(id);
        return productMapper.toDTOList(product);

    }

    public List<ProductDto> searchProducts(String keyword) {
        var product = productRepository.searchProducts(keyword);
        return productMapper.toDTOList(product);
    }

    public Page<ProductResponse> getAllCustomers(Pageable pageable) {
        var customerEntity = productRepository.findAllPagableData(pageable);
        return productMapper.toDTOPage((Page<Product>) customerEntity);
    }

    @Override
    public List<ProductDto> findProductsPagination(int offset, int pageSize) {
       List<ProductDto> products= productMapper.fromDTOPage(productRepository.findAll(PageRequest.of(offset,pageSize)));
       return  products;
    }
}
//    public Page<ProductDto> getUsers(int page, int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        var userPage = productRepository.findAll(pageRequest);
//        return productMapper.toDTOList(userPage);
//}
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