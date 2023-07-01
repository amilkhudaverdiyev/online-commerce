package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.service.ProductService;
import com.onlinefoodcommercems.utils.CakeHouseUtils;
import lombok.RequiredArgsConstructor;
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
        return  productService.findAllByActivated();
    }
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        try {
            productService.createProduct(request);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);
        } catch (NotDataFound ex) {
        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.NOTDATAFOUNDCATEGORY, HttpStatus.INTERNAL_SERVER_ERROR);
    }catch (Exception e) {
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}






//@PostMapping("/create/{category}")
//    public ResponseEntity<String> createCategory(@RequestBody ProductRequest request, @PathVariable Long category) {
//        try {
//            productService.save(request,category);
//            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }