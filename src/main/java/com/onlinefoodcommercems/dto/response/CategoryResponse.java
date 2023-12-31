package com.onlinefoodcommercems.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinefoodcommercems.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    @JsonIgnore
    Long id;
    String name;
    Status status;

}
