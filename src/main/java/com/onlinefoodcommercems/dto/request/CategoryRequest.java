package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    Long id;
    @NotBlank(message = "Name is not empty")
    String name;
    Status status=Status.ACTIVE;

}
