package com.onlinefoodcommercems.dto.update;

import com.onlinefoodcommercems.constraints.CardNumberValidation;
import com.onlinefoodcommercems.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class AccountUpdateRequest {
    @NotNull(message = "Amount must not empty")
    @Positive(message = "amount must  greater than 0")
    private BigDecimal amount;
    @CardNumberValidation
    @NotBlank(message = "Card number can not a blank")
    private String cardNumber;
    @NotNull(message = "Expiry date must not empty")
    private LocalDate expiryDate;
    private Status status=Status.ACTIVE;
}
