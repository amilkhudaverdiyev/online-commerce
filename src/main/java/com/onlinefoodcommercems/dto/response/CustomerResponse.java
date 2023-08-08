package com.onlinefoodcommercems.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String phoneNumber;
    private AddressResponse address;
    private Status status;
}
