package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.DiscountStatus;
import jakarta.validation.constraints.NotBlank;
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
    @Positive(message = "Quantity must be greater than 0")
    private double discount;
    @NotBlank(message = "Discount date is not empty")
    private LocalDateTime discountDate;
    @NotBlank(message = "End date is not empty")
    private LocalDateTime endDate;
    private ProductRequest product;
    private DiscountStatus status = DiscountStatus.LOADING;
}
