package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.Status;
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
    CategoryResponse category;
    Status status;

}
