package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

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
    private LocalDate birthDate;
    private String phoneNumber;
    private AddressResponse address;
    private Status status;
}
