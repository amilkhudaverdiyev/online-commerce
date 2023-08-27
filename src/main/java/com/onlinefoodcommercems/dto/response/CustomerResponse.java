package com.onlinefoodcommercems.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinefoodcommercems.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String activationCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String phoneNumber;
    private AddressResponse address;
    private Status accountStatus;
}
