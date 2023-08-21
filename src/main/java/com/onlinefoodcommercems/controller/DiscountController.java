package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseDetail createDiscount(@RequestBody DiscountRequest request) {

        discountService.addDiscount(request);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/all-active/{status}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<DiscountDto> getAllProductActivated (@PathVariable DiscountStatus status) {
        return discountService.findAllByActivated(status);
    }
}
