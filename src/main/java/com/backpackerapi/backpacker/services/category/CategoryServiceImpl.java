package com.backpackerapi.backpacker.services.category;

import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import com.backpackerapi.backpacker.mappers.CategoryMapper;
import com.backpackerapi.backpacker.models.Category;
import com.backpackerapi.backpacker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Set<CategoryDto> findAll() {
        return repository.findAll().stream().map(e -> mapper.entityToDto(e)).collect(Collectors.toSet());
    }

    @Override
    public Category findById(long id) {
        return repository.findById(id).orElse(null);
    }

}
