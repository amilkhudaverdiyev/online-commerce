package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.dto.update.CustomerUpdateReqeust;
import com.onlinefoodcommercems.entity.Customer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    Customer fromDTO(CustomerRequest customerRequest);


    CustomerResponse toDTO(Customer customer);

    List<CustomerResponse> toDTOs(List<Customer> customers);

    Customer toDTOmap(@MappingTarget Customer customer, CustomerUpdateReqeust customerRequest);
}
