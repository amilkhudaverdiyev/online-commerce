package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

        Product fromDTO (ProductRequest productRequest);

    ProductResponse toDTO(Product products);
    List<ProductDto> toDTOList(List<Product> products);
    List<ProductResponse> toDTOs(List<Product> products);
}
