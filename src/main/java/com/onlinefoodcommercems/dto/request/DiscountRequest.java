package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountRequest {
    private Long id;

    private double discount;

    private LocalDateTime discountDate;
    private LocalDateTime endDate;
    private ProductRequest product;
    private DiscountStatus status= DiscountStatus.LOADING;
}
