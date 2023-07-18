package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    //DiscountRequest discountRequest;
    Status status=Status.ACTIVE;
}
