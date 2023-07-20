package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.service.DiscountService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountController {
    private final DiscountService discountService;
    @PostMapping
    public ResponseEntity<String> createDiscount(@RequestBody DiscountRequest request) {

            discountService.addDiscount(request);
            return MessageUtils.getResponseEntity(Messages.SUCCESSFULLY, HttpStatus.CREATED);



    }
    @GetMapping("/all-active")
    public List<DiscountDto> getAllProductActivated() {
        return discountService.findAllByActivated();
    }
//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteDiscount() {
//
//        discountService.terminatedDiscount();
//        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.CREATED);

}
