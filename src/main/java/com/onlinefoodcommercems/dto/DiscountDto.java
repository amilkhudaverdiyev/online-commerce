package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DiscountDto (
         Long id,
         Double discount,
         Double discountPrice,
         LocalDateTime discountDate,
         LocalDateTime endDate
        ){}