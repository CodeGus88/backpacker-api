package com.backpackerapi.backpacker.controllers.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CategoryController {

    ResponseEntity<Set<CategoryDto>> findAll();

    ResponseEntity<Category> findBiId(long id);

}
