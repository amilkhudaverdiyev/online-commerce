package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        Integer currentQuantity,
        Double unitPrice,
        CategoryResponse category,
       List<DiscountResponse> discount
) {}
