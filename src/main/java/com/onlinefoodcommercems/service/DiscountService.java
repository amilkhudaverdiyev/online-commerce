package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountService {
     DiscountResponse addDiscount(DiscountRequest discountRequest);
     void terminatedDiscount(Discount discount);
     void activatedDiscount(Discount discount);
     List<DiscountDto> findAllByActivated(Status status);
}
