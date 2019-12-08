package com.application.app.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepositoryInterface extends JpaRepository<Ingredient, Long> {
//    List<Ingredient> findAllByName(List<String> ingredientNames);
    Ingredient findByName(String ingredientName);
//    void deleteIngredientByName(String ingredientName);
}
