package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.constraints.AgeValidation;
import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.Valid;
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
public class CustomerRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Surname cannot be empty")
    private String surname;
    @Email(message = "Incorrect email!")
    @NotBlank(message = "Email cannot be empty")
    private String username;
    @Size(min = 3, max = 15, message = "Password contains 3-10 characters")
    @NotBlank(message = "Password is not empty")
    private String password;

    @AgeValidation
    @NotNull(message = "Birth Date is not empty")
    private LocalDate birthDate;
    @Size(min = 10, max = 15, message = "Phone number contains 10-15 characters")
    @NotBlank(message = "Phone number is not empty")
    private String phoneNumber;
    @Valid
    private AddressRequest address;
    private Status accountStatus = Status.DEACTIVE;

}
