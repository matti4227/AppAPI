package com.application.app.cookbook;

import com.application.app.recipe.Recipe;
import com.application.app.recipe.RecipePageResponse;

public interface CookbookServiceInterface {

    void createCookbook(Long id);

    Cookbook getCookbook(Long cookbookId);

    Recipe getRecipeFromCookbook(Long recipeId);

    Cookbook addRecipe(Long recipeId, Long cookbookId);

    Cookbook removeRecipe(Long recipeId, Long cookbookId);//List<Long>

    RecipePageResponse getCookbookByCookbook(int page);

    Cookbook getCookbookByUser();

    Cookbook removeRecipeFromCookbook(Long recipeId);
}
