package com.application.app.recipe;

import com.application.app.ingredient.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeServiceInterface {

    Recipe createRecipe(RecipeRequest recipe);

    Recipe getRecipe(Long id);

    List<Recipe> getRecipes();

    Recipe updateRecipe(Long id, RecipeRequest recipeRequest);

    void deleteRecipe(Long id);

    Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients);

    void removeIngredients(Recipe recipe);

//    Ingredient getIngredientFromRecipe(Long recipeId, String ingredientName);
}
