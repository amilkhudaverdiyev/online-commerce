package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.repository.DiscountRepository;
import lombok.Builder;

import java.util.List;


@Builder
public record ItemResponse(
        Long id,
        String name,
        String description,
        Integer currentQuantity,
        Double unitPrice,
        CategoryDto category
        ) {}