package com.onlinefoodcommercems.dto.response;

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
public class AccountResponse {
    private String accountId;

    private String fullname;

    private Long id;

    private BigDecimal amount;
    private String cardNumber;
    private LocalDate expiryDate;
    private Status status;


}
