package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.CartItemService;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Long id;
    ItemResponse product;
    Integer quantity;
    Double price;
    Double totalPrice;




}
