package com.onlinefoodcommercems.dto.update;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    String name;
    String description;
    @Positive(message = "The current quantity must be positive")
    Integer currentQuantity;
    @Positive(message = "The unit-price must be positive")
    Double unitPrice;
}
