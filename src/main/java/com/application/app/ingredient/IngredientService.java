package com.application.app.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService implements IngredientServiceInterface {

    @Autowired
    private IngredientRepositoryInterface ingredientRepositoryInterface;

    @Override
    public Ingredient addIngredient(String ingredientName) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);
        return ingredientRepositoryInterface.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepositoryInterface.findAll();
    }

    @Override
    public List<Ingredient> getIngredients(List<IngredientRequest> ingredientRequests) {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient;

        for (int x = 0; x < ingredientRequests.size(); x++) {
            ingredient = getIngredient(ingredientRequests.get(x).getName());
            ingredients.add(ingredient);
        }

        return ingredients;
    }

//    public List<Ingredient> getIngredients(List<RecipeRequest> ingredientNames ) {
//
//        Ingredient ingredient;
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        for (int x = 0; x < ingredientNames.size(); x++) {
//            ingredient = ingredientRepositoryInterface.findByName(ingredientNames.get(x).getName());
//            ingredients.add(ingredient);
//        }
//
//        return ingredients;
//    }
//
//    private Ingredient getIngredientObject() {
//        return new Ingredient();
//    }

    @Override
    public Ingredient getIngredient(String ingredientName) {
        return ingredientRepositoryInterface.findByName(ingredientName);
    }

    public void deleteIngredient(Long ingredientId) {
        ingredientRepositoryInterface.deleteById(ingredientId);
    }
}
