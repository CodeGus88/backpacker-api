package com.backpackerapi.backpacker.mappers;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper{
    CategoryDto entityToDto(Category category);
}
