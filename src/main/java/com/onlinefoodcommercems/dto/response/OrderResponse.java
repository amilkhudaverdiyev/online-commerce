package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    private Long orderId;
     Date orderDate;
     Date deliveryDate;
    Double totalAmount;
     OrderStatus status;
     List<OrderDetailResponse> orderDetails;



}
