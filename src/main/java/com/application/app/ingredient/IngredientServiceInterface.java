package com.application.app.ingredient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientServiceInterface {

    Ingredient addIngredient(String ingredientName);

    List<Ingredient> getAllIngredients();

    List<Ingredient> getIngredients(List<IngredientRequest> ingredientRequests);

    Ingredient getIngredient(String ingredientName);

    void deleteIngredient(Long ingredientId);
}
