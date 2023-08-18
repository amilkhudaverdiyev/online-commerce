package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        Integer currentQuantity,
        Double unitPrice,
        CategoryDto category

) {
}
