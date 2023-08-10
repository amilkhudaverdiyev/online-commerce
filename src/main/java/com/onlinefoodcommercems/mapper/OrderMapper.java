package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.request.OrderRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.entity.Order;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    Order fromDTO (OrderRequest orderRequest);

    OrderResponse toDTO(Order order);

    List<OrderResponse> toDTOs(List<Order> orders);


}
