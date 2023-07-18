//package com.onlinefoodcommercems.mapper;
//
//import com.onlinefoodcommercems.dto.request.AddressRequest;
//import com.onlinefoodcommercems.dto.request.CustomerRequest;
//import com.onlinefoodcommercems.dto.response.AddressResponse;
//import com.onlinefoodcommercems.dto.response.CustomerResponse;
//import com.onlinefoodcommercems.entity.Address;
//import com.onlinefoodcommercems.entity.Customer;
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
//public interface CustomerMapper {
//    Customer fromDTO (CustomerRequest customerRequest);
//
//
//    CustomerResponse toDTO(Customer customer);
//    //CategoryDto toDTOId(Category category);
//    List<CustomerResponse> toDTOs(List<Customer> customers);
//    // List<CategoryDto> toDTOList(List<Category> categories);
//}
