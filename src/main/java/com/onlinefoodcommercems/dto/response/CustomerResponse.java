package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer age;
    private String phoneNumber;
    private AddressResponse address;
    private Roles role;
    private Status status;
}
