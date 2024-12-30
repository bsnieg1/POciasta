package com.example.cookingapp.repository;

import com.example.cookingapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Możesz dodać własne metody, np. wyszukiwanie po nazwie
    // List<Recipe> findByName(String name);
}
