package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String description;
    Integer currentQuantity;
    Double unitPrice;
    CategoryResponse category;
}
