package com.backpackerapi.backpacker.repositories;

import com.backpackerapi.backpacker.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    List<Category> findAllByOrderByNameAsc();

}
