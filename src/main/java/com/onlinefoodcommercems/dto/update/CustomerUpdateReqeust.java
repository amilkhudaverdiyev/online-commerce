package com.onlinefoodcommercems.dto.update;

import com.onlinefoodcommercems.constraints.AgeValidation;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Surname cannot be empty")
    private String surname;
    @AgeValidation
    @NotNull
    private LocalDate birthDate;
    @Size(min = 10, max = 15, message = "Phone number contains 10-15 characters")
    private String phoneNumber;
    private AddressRequest address;
}
