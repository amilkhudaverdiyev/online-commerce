package com.onlinefoodcommercems.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinefoodcommercems.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    private Long orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm:ss")
     LocalDateTime orderDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm:ss")
     LocalDateTime deliveryDate;
    Double totalAmount;
     OrderStatus status;
     List<OrderDetailResponse> orderDetails;



}
