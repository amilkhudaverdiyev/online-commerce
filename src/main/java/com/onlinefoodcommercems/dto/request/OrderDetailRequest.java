package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {
    private Long id;
    ProductRequest product;
    CustomerRequest customer;
    Integer quantity;
    Double price;
    Double totalPrice;

}
