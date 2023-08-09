package com.backpackerapi.backpacker.controllers.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {

    ResponseEntity<List<CategoryDto>> findAll();

    ResponseEntity<Category> findBYId(long id);

}
