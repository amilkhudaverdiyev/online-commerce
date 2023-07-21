package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.entity.Address;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    Address fromDTO (AddressRequest addressRequest);


    AddressResponse toDTO(Address address);
    //CategoryDto toDTOId(Category category);
    List<AddressResponse> toDTOs(List<Address> addresses);
    Address toDTOmap(@MappingTarget Address address, AddressRequest addressRequest);
   // List<CategoryDto> toDTOList(List<Category> categories);

}
