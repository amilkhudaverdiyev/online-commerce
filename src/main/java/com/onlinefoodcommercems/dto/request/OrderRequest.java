package com.onlinefoodcommercems.dto.request;

import com.onlinefoodcommercems.enums.OrderStatus;
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
     Double totalAmount;
     CustomerRequest customer;
     OrderStatus status= OrderStatus.LOADING;
     OrderDetailRequest orderDetail;


}
