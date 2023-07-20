package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
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

    @GetMapping("/all-active")
    public List<ProductDto> getAllProductActivated() {
        return productService.findAllByActivated();
    }


    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return MessageUtils.getResponseEntity(Messages.SUCCESSFULLY, HttpStatus.CREATED);

    }


    @GetMapping("/id/{id}")
    public List<ProductDto> findProductByCategory(@PathVariable Long id) {

        return productService.findByCategoryId(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProduct(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/alls")
    public Page<ProductDto> getAllCustomers(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }
    @RequestMapping(value = "/deleted-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return MessageUtils.getResponseEntity(Messages.DELETE_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MessageUtils.getResponseEntity(Messages.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @RequestMapping(value = "/enabled-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledProduct(@PathVariable Long id) {
        try {
            productService.enableById(id);
            return MessageUtils.getResponseEntity(Messages.ENABLED_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MessageUtils.getResponseEntity(Messages.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
