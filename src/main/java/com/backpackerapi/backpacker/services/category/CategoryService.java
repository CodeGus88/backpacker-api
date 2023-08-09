package com.backpackerapi.backpacker.services.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    Category findById(long id);
}
