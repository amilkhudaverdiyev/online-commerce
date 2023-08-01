package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.OrderDetailRequest;
import com.onlinefoodcommercems.dto.request.OrderRequest;
import com.onlinefoodcommercems.dto.response.OrderDetailResponse;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.OrderDetail;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {
    OrderDetail fromDTO (OrderDetailRequest orderDetailRequest);

    OrderDetailResponse toDTO(OrderDetail orderDetail);
    //OrderDetail toDTOq(List<OrderDetail> orderDetail);

    List<OrderDetailResponse> toDTOs(List<OrderDetail> orderDetails);
    List<OrderResponse> toDTOm(List<OrderDetail> orderDetails);
    List<OrderDetail> cartToOrderDetail(List<CartItem> cartItems);

   // Address toDTOmap(@MappingTarget Address address, AddressRequest addressRequest);

}
