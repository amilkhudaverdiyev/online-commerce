package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Fullname is not empty")
    private String fullname;

    private Long id;
    @NotBlank(message = "Amount is not empty")
    private BigDecimal amount;
    //   @CardNumberValidation
    private String cardNumber;
    private LocalDate expiryDate;
    private Status status = Status.DEACTIVE;


}
