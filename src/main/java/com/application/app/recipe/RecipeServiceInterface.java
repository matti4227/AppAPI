package com.application.app.recipe;

import com.application.app.ingredient.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeServiceInterface {

    Recipe createRecipe(RecipeRequest recipe);

    Recipe getRecipe(Long id);

//    RecipePageResponse getRecipes(int page);

    RecipePageResponse getRecipesByParameters(int difficulty, int preparationTime, int sort, int page);

    RecipePageResponse getRecipesByNameSearch(String name, int page);

    Recipe updateRecipe(Long id, RecipeRequest recipeRequest);

    void deleteRecipe(Long id);

    Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients);

    void removeIngredients(Recipe recipe);

//    Ingredient getIngredientFromRecipe(Long recipeId, String ingredientName);
}
