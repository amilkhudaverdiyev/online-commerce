//package com.onlinefoodcommercems.mapper;
//
//import com.onlinefoodcommercems.dto.request.AddressRequest;
//import com.onlinefoodcommercems.dto.request.CartItemRequest;
//import com.onlinefoodcommercems.dto.response.AddressResponse;
//import com.onlinefoodcommercems.dto.response.CartItemResponse;
//import com.onlinefoodcommercems.entity.Address;
//import com.onlinefoodcommercems.entity.CartItem;
//import org.mapstruct.InjectionStrategy;
//import org.mapstruct.Mapper;
//import org.mapstruct.ReportingPolicy;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Mapper(componentModel = "spring",
//        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
//        unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface CartItemMapper {
//    CartItem fromDTO (CartItemRequest cartItemRequest);
//
//
//    CartItemResponse toDTO(CartItem cartItem);
//    //CategoryDto toDTOId(Category category);
//    List<CartItemResponse> toDTOs(List<CartItem> cartItems);
//    // List<CategoryDto> toDTOList(List<Category> categories);
//}
