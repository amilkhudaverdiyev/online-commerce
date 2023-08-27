package com.onlinefoodcommercems.dto;

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
