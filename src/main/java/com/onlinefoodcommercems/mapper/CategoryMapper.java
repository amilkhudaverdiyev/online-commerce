package com.onlinefoodcommercems.mapper;


import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.update.CategoryUpdateRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category fromDTO (CategoryRequest categoryRequest);


    CategoryResponse toDTO(Category category);
    CategoryDto toDTOId(Category category);
    List<CategoryResponse> toDTOs(List<Category> categories);
    List<CategoryDto> toDTOList(List<Category> categories);
    Category toDTOmap(@MappingTarget Category category, CategoryUpdateRequest categoryRequest);
}
