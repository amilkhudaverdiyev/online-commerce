package com.onlinefoodcommercems.dto;

import lombok.Builder;


@Builder
public record ItemResponse(
        Long id,
        String name,
        CategoryDto category
) {
}