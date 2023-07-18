package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Discount;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountService {
     DiscountResponse addDiscount(DiscountRequest discountRequest);
     void terminatedDiscount(Discount discount);
      List<Discount> getCustomerByStatus();
     void activatedDiscount(Discount discount);
     //void updateProductUnitPrice(Discount discount);

}
