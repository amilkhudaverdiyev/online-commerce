package com.onlinefoodcommercems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    String description;
    Integer currentQuantity;
    Double unitPrice;
    Double costPrice;
    CategoryResponse category;
    //DiscountResponse discount;

}
