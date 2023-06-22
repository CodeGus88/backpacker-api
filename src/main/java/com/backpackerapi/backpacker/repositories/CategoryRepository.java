package com.backpackerapi.backpacker.repositories;

import com.backpackerapi.backpacker.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("SELECT c.id id, c.name name, c.description description FROM Category c")
//    List<Category> findAll();

    boolean existsByName(String name);
}
