package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Responses;
import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.service.ProductService;
import com.onlinefoodcommercems.utils.MessageUtils;
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


    @GetMapping("/all")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return MessageUtils.getResponseEntity(Responses.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public List<ItemResponse> findProductsByCategory(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProduct(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @RequestMapping(value = "/deleted-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return MessageUtils.getResponseEntity(Responses.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enabled-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledProduct(@PathVariable Long id) {
        productService.enableById(id);
        return MessageUtils.getResponseEntity(Responses.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }
}
