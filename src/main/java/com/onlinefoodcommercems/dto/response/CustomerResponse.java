package com.onlinefoodcommercems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String phoneNumber;
    private AddressResponse address;

    private boolean activated=true;

    private boolean deleted=false;
}
