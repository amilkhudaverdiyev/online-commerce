package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.constraints.CardNumberValidation;
import com.onlinefoodcommercems.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountRequest {

    private String accountId;

    private String fullname;

    private Long id;

    private BigDecimal amount;
//   @CardNumberValidation
    private String cardNumber;
    private LocalDate expiryDate;
    private Status status;


}
