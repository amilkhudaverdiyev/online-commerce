package com.onlinefoodcommercems.dto.response;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.entity.OrderDetail;
import com.onlinefoodcommercems.enums.OrderAcceptStatus;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.PaymentMethod;
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
     OrderStatus orderStatus;
    Double totalAmount;
     PaymentMethod paymentMethod;
     OrderAcceptStatus acceptStatus;
     List<OrderDetailResponse> orderDetails;



}
