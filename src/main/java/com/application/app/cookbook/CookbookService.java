package com.application.app.cookbook;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.applicationUser.ApplicationUserService;
import com.application.app.recipe.Recipe;
import com.application.app.recipe.RecipeRepositoryInterface;
import com.application.app.recipe.RecipeService;
import com.application.app.security.SecurityService;
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

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SecurityService securityService;

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
    public Recipe getRecipeFromCookbook(Long recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
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

        Recipe recipe = getRecipeFromCookbook(recipeId);
        Cookbook cookbook = getCookbook(cookbookId);

        cookbook = cookbookRepository.removeRecipe(cookbook, recipe);

        return cookbookRepositoryInterface.save(cookbook);
    }

    @Override
    public Cookbook getCookbookByUser() {
        String username = securityService.getUsernameFromUserDetails();
        ApplicationUser user = userService.getUserByName(username);

        return cookbookRepositoryInterface.findCookbookByUser(user);
    }

    @Override
    public Cookbook removeRecipeFromCookbook(Long recipeId) {
        Recipe recipe = getRecipeFromCookbook(recipeId);
        Cookbook cookbook = getCookbookByUser();

        cookbook = cookbookRepository.removeRecipe(cookbook, recipe);

        return cookbookRepositoryInterface.save(cookbook);
    }

}
