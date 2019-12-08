package com.application.app.cookbook;

import com.application.app.recipe.Recipe;

public interface CookbookServiceInterface {

    void createCookbook(Long id);

    Cookbook getCookbook(Long cookbookId);

    Recipe getRecipeFromCookbook(Long cookbookId, Long recipeId);

    Cookbook addRecipe(Long recipeId, Long cookbookId);

    Cookbook removeRecipe(Long recipeId, Long cookbookId);//List<Long>
}
