package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountResponse {
    private Long id;
    private double discount;
    private LocalDateTime discountDate;
    private LocalDateTime endDate;
    private ProductResponse productResponse;

}
