package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.dto.update.ProductUpdateRequest;
import com.onlinefoodcommercems.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllProductCount() {
        log.error("count " + productService.getAllProductCount());
        return productService.getAllProductCount();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail createProduct(@RequestBody @Valid ProductRequest request) {
        log.error("product  {}", request);
        log.error("product {}", productService.createProduct(request));
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @DeleteMapping(value = "/deleted-product/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDetail deletedProduct(@PathVariable Long id) {
        log.error("product  {}", id);
        productService.deleteById(id);
        log.error("product  {}", id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @PutMapping(value = "/enabled-product/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail enabledProduct(@PathVariable Long id) {
        log.error("product  {}", id);
        productService.enableById(id);
        log.error("product  {}", id);
        return ResponseDetail.builder()
                .message(ResponseMessage.ENABLED_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail updateProduct(@PathVariable Long id,
                                        @RequestBody @Valid ProductUpdateRequest request) {
        log.error("id  {}, product {}", id, request);
        productService.update(id, request);
        log.error("id  {}, product {}", id, request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PutMapping(value = "/update-increaseAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail increaseAllPriceWithPercentage(@RequestParam
                                                         @Positive(message = ResponseMessage.PERCENT_VALID)
                                                         Double percent) {
        productService.increaseAllPrice(percent);
        log.error("id  {}, percent {}", percent);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PutMapping(value = "/update-decreaseAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail decreaseAllPriceWithPercentage(@RequestParam
                                                         @Positive(message = ResponseMessage.PERCENT_VALID)
                                                         Double percent) {
        log.error("id  {}, product {}", percent);
        productService.decreaseAllPrice(percent);
        log.error("id  {}, product {}", percent);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
