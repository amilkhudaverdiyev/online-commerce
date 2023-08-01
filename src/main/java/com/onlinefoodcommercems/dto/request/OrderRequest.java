package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.enums.OrderAcceptStatus;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private Long id;
     Date deliveryDate;
     OrderStatus orderStatus=OrderStatus.LOADING;
     String paymentMethod;
     Double totalAmount;
     CustomerRequest customer;
     OrderAcceptStatus acceptStatus=OrderAcceptStatus.LOADING;
     OrderDetailRequest orderDetail;


}
