package com.onlinefoodcommercems.dto.update;

import com.onlinefoodcommercems.constraints.AgeValidation;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateReqeust {

    private String name;
    private String surname;
    @AgeValidation
    private LocalDate birthDate;
    @Size(min = 10, max = 15, message = "Phone number contains 10-15 characters")
    private String phoneNumber;

    private AddressRequest address;
}
