package com.onlinefoodcommercems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    Integer id;
    String area;
    String city;
    String state;
    String country;
    String pinCode;
}
