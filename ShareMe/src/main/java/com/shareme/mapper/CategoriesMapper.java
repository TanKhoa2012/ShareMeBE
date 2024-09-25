package com.shareme.mapper;

import com.shareme.dto.response.CategoriesResponse;
import com.shareme.entity.Categories;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {
    CategoriesResponse toCategoriesResponse(Categories categories);
}
