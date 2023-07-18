package com.onlinefoodcommercems.dto;

import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
public record ProductDto (
    Long id,
    String name,
    String description,
    Integer currentQuantity,
    Double unitPrice,
    CategoryResponse category ){

}
