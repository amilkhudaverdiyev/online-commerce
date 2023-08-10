package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.CartItem;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartItemMapper {
    CartItem fromDTO(CartItemRequest cartItemRequest);


    CartItemResponse toDTO(CartItem cartItem);

    List<CartItemResponse> toDTOs(List<CartItem> cartItems);

    CartItem toDTOmap(@MappingTarget CartItem cartItem, CartItemRequest cartItemRequest);
}
