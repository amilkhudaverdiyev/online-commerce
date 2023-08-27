package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.DiscountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountResponse {
    private Long id;
    private double discount;
    private double discountPrice;
    private LocalDateTime discountDate;
    private LocalDateTime endDate;
    private DiscountStatus status;

}
