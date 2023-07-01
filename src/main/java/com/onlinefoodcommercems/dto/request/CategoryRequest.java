package com.onlinefoodcommercems.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    Integer id;
    String name;
    Boolean activated=true;
    Boolean deleted=false;
}
