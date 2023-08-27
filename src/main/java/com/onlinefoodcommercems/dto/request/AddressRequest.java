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
public class AddressRequest {
    Long id;
    @NotBlank(message = "Country is not empty")
    String country;
    @NotBlank(message = "City is not empty")
    String city;
    @NotBlank(message = "District is not empty")
    String district;
    @NotBlank(message = "Street is not empty")
    String street;
    @NotBlank(message = "Apartment number is not empty")
    String apartmentNumber;
    Status status = Status.ACTIVE;
}
