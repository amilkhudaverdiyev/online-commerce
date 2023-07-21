package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
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
    CustomerRequest customerRequest;
    ProductRequest productRequest;
    Status status=Status.ACTIVE;
}
