package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "payment")
@ToString
@Builder
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    private String senderId;

    private String receiverId;

    private BigDecimal payment;

    @UpdateTimestamp
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
