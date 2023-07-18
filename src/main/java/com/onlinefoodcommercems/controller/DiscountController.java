package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.service.DiscountService;
import com.onlinefoodcommercems.utils.CakeHouseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountController {
    private final DiscountService discountService;
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody DiscountRequest request) {

            discountService.addDiscount(request);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);



    }
//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteDiscount() {
//
//        discountService.terminatedDiscount();
//        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);

}
