package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    String name;
    String description;
    Integer currentQuantity;
    Double unitPrice;
    Long categoryId;
    Boolean activated=true;
    Boolean deleted=false;
}
