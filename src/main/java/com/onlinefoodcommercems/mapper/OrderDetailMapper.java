package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.OrderDetailRequest;
import com.onlinefoodcommercems.dto.response.OrderDetailResponse;
import com.onlinefoodcommercems.entity.CartItem;
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
    OrderDetail fromDTO(OrderDetailRequest orderDetailRequest);

    OrderDetailResponse toDTO(OrderDetail orderDetail);

    List<OrderDetailResponse> toDTOs(List<OrderDetail> orderDetails);

    List<OrderDetail> cartToOrderDetail(List<CartItem> cartItems);


}
