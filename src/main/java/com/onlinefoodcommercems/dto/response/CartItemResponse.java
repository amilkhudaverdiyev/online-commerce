package com.onlinefoodcommercems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Integer id;
    Integer quantity;
    Double price;
    Double totalPrice;
    CustomerResponse customerResponse;
    ProductResponse productResponse;
}
