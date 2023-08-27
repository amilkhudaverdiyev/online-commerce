package com.onlinefoodcommercems.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemRequest {
    Long cartId;

    Long id;

    @Positive(message = "Quantity must be positive")
    Integer quantity;

    @Positive(message = "Quantity must be positive")
    Double price;

    @Positive(message = "Quantity must be positive")
    Double totalPrice;
}
