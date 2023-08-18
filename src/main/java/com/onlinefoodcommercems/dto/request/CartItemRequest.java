package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemRequest {
   Long id;
    Integer quantity;
    Double price;
   Double totalPrice;
}
