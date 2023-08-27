package com.onlinefoodcommercems.mapper;

import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.dto.request.ProductRequest;
import com.onlinefoodcommercems.dto.response.ProductResponse;
import com.onlinefoodcommercems.dto.update.ProductUpdateRequest;
import com.onlinefoodcommercems.entity.Product;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product fromDTO(ProductRequest productRequest);

    ProductResponse toDTO(Product products);

    List<ProductDto> toDTOList(List<Product> products);

    List<ItemResponse> productToItemResponse(List<Product> products);

    List<ProductResponse> toDTOs(List<Product> products);

    Product toDTOmap(@MappingTarget Product product, ProductUpdateRequest productRequest);

}
