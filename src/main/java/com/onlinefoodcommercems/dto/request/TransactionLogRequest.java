package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionLogRequest {
    private String id;

    private String senderId;

    @NotBlank(message = "Receiver account must not empty")
    private String receiverId;

    @Positive(message = ResponseMessage.PAYMENT_VALID)
    private BigDecimal payment;
    private PaymentStatus status = PaymentStatus.CANCELED;
}
