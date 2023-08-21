package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionLogResponse {
    private String id;

    private String senderId;
    private String receiverId;

    private BigDecimal payment;
    private LocalDateTime date;
    private PaymentStatus status;
}
