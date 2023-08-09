package com.backpackerapi.backpacker.controllers.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.models.Category;
import com.backpackerapi.backpacker.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/categories")
public class CategoryControllerImpl implements CategoryController{

    @Autowired
    private CategoryService service;

    @GetMapping
    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<Category> findBYId(@PathVariable long id) {
        Category category = service.findById(id);
        if(category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);
    }
}
