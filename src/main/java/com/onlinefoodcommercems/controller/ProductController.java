package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.service.ProductService;
import com.onlinefoodcommercems.utils.CakeHouseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all-active")
    public List<ProductDto> getAllProductActivated() {
        return productService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
            productService.createProduct(request);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);

        }


    @GetMapping("/id/{id}")
    public List<ProductDto> findProductByCategory(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProduct(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
    @GetMapping("/all/{offset}/{pageSize}")
    public List<ProductDto> getAllCustomers(@PathVariable int offset,
                                            @PathVariable int pageSize) {
        return productService.findProductsPagination(offset,pageSize);
    }
    @GetMapping("/alls")
    public Page<ProductResponse> getAllCustomers(@RequestParam Pageable pageable) {
        return productService.getAllCustomers(pageable);
    }//islemir

}
