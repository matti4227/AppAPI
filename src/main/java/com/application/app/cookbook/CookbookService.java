package com.application.app.cookbook;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.applicationUser.ApplicationUserService;
import com.application.app.recipe.Recipe;
import com.application.app.recipe.RecipeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookbookService implements CookbookServiceInterface {

    @Autowired
    private CookbookRepositoryInterface cookbookRepositoryInterface;

    @Autowired
    private CookbookRepository cookbookRepository;

    @Autowired
    private RecipeRepositoryInterface recipeRepositoryInterface;

    @Autowired
    private ApplicationUserService userService;

    @Override
    public void createCookbook(Long id) {
        ApplicationUser user = userService.getUser(id);
        Cookbook cookbook = new Cookbook();
        cookbook.setUser(user);
        cookbookRepositoryInterface.save(cookbook);
    }

    @Override
    public Cookbook getCookbook(Long cookbookId) {
        return cookbookRepositoryInterface.findById(cookbookId).orElse(null);
    }

    @Override
    public Recipe getRecipeFromCookbook(Long cookbookId, Long recipeId) {

        Cookbook cookbook = getCookbook(cookbookId);
        Recipe recipe = null;

        List<Recipe> recipes = cookbook.getRecipes();
        for (int x = 0; x < recipes.size(); x++) {
            if (recipeId == recipes.get(x).getId()) {
                recipe = recipes.get(x);
                break;
            }
        }
        return recipe;
    }

    @Override
    public Cookbook addRecipe(Long recipeId, Long cookbookId) {
        Recipe recipe = recipeRepositoryInterface.findById(recipeId).orElse(null);
        Cookbook cookbook = cookbookRepositoryInterface.findById(cookbookId).orElse(null);

        cookbook = cookbookRepository.addRecipe(cookbook, recipe);
        cookbookRepositoryInterface.save(cookbook);
        return cookbook;
    }

    @Override
    public Cookbook removeRecipe(Long recipeId, Long cookbookId) {

        Recipe recipe = getRecipeFromCookbook(cookbookId, recipeId);
        Cookbook cookbook = getCookbook(cookbookId);

        cookbook = cookbookRepository.removeRecipe(cookbook, recipe);

        return cookbookRepositoryInterface.save(cookbook);
    }

}
