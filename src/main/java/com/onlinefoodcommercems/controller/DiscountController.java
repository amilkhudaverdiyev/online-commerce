package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.service.DiscountService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createDiscount(@RequestBody DiscountRequest request) {

        discountService.addDiscount(request);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping("/all-active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<DiscountDto> getAllProductActivated() {
        return discountService.findAllByActivated();
    }
}
