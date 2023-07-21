package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    Long id;
    String country;
    String city;
    String district;
    String street;
    String apartmentNumber;
    Status status;
}
