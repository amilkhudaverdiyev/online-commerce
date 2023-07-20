package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.entity.Discount;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    Discount fromDTO(DiscountRequest discountRequest);


    DiscountResponse toDTO(Discount discount);
    DiscountDto toDTOm(Discount discount);

    //CategoryDto toDTOId(Category category);
    List<DiscountResponse> toDTOs(List<Discount> discounts);
    List<DiscountDto> toDTOList(List<Discount> discounts);
    //List<CategoryDto> toDTOList(List<Category> categories);
}