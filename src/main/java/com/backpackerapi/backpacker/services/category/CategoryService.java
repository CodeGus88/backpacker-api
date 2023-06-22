package com.backpackerapi.backpacker.services.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<CategoryDto> findAll();
    Category findById(long id);
}
