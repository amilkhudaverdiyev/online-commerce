package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    @NotBlank(message = "Name is not empty")
    String name;
    String description;
    @Positive(message = "The current quantity must be positive")
    Integer currentQuantity;
    @Positive(message = "The unit-price must be positive")
    @NotNull(message = "Unit price must not empty")
    Double unitPrice;
    @NotNull(message = "Category is not empty")
    Long categoryId;
    Status status = Status.ACTIVE;
}
