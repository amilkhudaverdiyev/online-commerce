package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.dto.ProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    private Long id;
    ProductDto product;
    //CustomerResponse customer;
    Integer quantity;
    Double price;
    Double totalPrice;


}
