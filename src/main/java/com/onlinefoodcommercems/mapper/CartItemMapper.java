package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.entity.Customer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartItemMapper {
    CartItem fromDTO (CartItemRequest cartItemRequest);


    CartItemResponse toDTO(CartItem cartItem);
    //CategoryDto toDTOId(Category category);
    List<CartItemResponse> toDTOs(List<CartItem> cartItems);
    CartItem toDTOmap(@MappingTarget CartItem cartItem, CartItemRequest cartItemRequest);
    // List<CategoryDto> toDTOList(List<Category> categories);
}
