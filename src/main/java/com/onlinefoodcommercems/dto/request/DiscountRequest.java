package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.DiscountStatus;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountRequest {
    private Long id;
    @Positive
    private double discount;
    private LocalDateTime discountDate;
    private LocalDateTime endDate;
    private ProductRequest product;
    private DiscountStatus status = DiscountStatus.LOADING;
}
