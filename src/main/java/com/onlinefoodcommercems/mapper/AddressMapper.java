package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.entity.Address;
import com.onlinefoodcommercems.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Mapper(componentModel = "spring",
//        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
//        unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface AddressMapper {
//    Address fromDTO (AddressRequest addressRequest);
//
//
//    AddressResponse toDTO(Address address);
//    //CategoryDto toDTOId(Category category);
//    List<AddressResponse> toDTOs(List<Address> addresses);
//   // List<CategoryDto> toDTOList(List<Category> categories);

//}
