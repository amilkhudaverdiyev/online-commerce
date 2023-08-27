package com.onlinefoodcommercems.dto.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressUpdateRequest {
    String country;
    String city;
    String district;
    String street;

    String apartmentNumber;
}
