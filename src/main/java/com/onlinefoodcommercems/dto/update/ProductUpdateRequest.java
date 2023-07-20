package com.onlinefoodcommercems.dto.update;

import lombok.Builder;

@Builder
public record ProductUpdateRequest(
        String name,
        String description,
        Integer currentQuantity,
        Double unitPrice,
        Long categoryId
){
}
