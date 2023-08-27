package com.onlinefoodcommercems.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DiscountDto(
        Long id,
        Double discount,
        Double discountPrice,
        LocalDateTime discountDate,
        LocalDateTime endDate
) {
}