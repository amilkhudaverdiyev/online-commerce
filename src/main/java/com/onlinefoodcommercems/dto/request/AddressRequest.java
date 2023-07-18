package com.onlinefoodcommercems.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    Integer id;
    String area;
    String city;
    String state;
    String country;
    String pinCode;
}
