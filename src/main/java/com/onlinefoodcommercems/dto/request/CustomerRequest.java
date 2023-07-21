package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Integer age;
    private String phoneNumber;
    private AddressRequest address;
    private Roles role= Roles.USER;

    private Status status= Status.ACTIVE;
}
