package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.service.ProductService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleted-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize(value = "hasAuthority(Roles.ADMIN)")
    public ResponseEntity<String> deletedProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enabled-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> enabledProduct(@PathVariable Long id) {
        productService.enableById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/update-increaseAll", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> increaseAllPriceWithPercentage(@RequestParam Double percent) {
        productService.increaseAllPrice(percent);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/update-decreaseAll", method = {RequestMethod.GET, RequestMethod.PUT})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> decreaseAllPriceWithPercentage(@RequestParam Double percent) {
        productService.decreaseAllPrice(percent);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }
}
