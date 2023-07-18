package com.onlinefoodcommercems.dto.request;

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
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;

    private boolean activated=true;

    private boolean deleted=false;
}
