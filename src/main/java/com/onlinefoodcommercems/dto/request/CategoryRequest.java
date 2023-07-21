package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    Long id;
    String name;
    Status status=Status.ACTIVE;

}
