package com.application.app.recipe;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.applicationUser.ApplicationUserService;
import com.application.app.ingredient.Ingredient;
import com.application.app.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "recipeService")
public class RecipeService implements RecipeServiceInterface {

    @Autowired
    private RecipeRepositoryInterface recipeRepositoryInterface;

    @Autowired
    private ApplicationUserService userService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public Recipe createRecipe(RecipeRequest recipeRequest) {
        ApplicationUser user = userService.getUser(recipeRequest.getUserId());
        Recipe recipe = recipeRepository.createRecipe(recipeRequest, user);

        recipe = recipeRepositoryInterface.save(recipe);

        if (recipeRequest.getIngredients().size() > 0) {
            List<Ingredient> ingredients = ingredientService.getIngredients(recipeRequest.getIngredients());
            addIngredients(recipe, ingredients);
        }

        return recipeRepositoryInterface.save(recipe);
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeRepositoryInterface.findById(id).orElse(null);
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepositoryInterface.findAll();
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepositoryInterface.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Long recipeId, RecipeRequest recipeRequest) {
        Recipe originalRecipe = getRecipe(recipeId);
        Recipe recipe = recipeRepository.updateRecipe(originalRecipe, recipeRequest);

        if (recipeRequest.getIngredients().size() > 0) {
            removeIngredients(recipe);
            List<Ingredient> ingredients = ingredientService.getIngredients(recipeRequest.getIngredients());
            addIngredients(recipe, ingredients);
        }

        return recipeRepositoryInterface.save(recipe);
    }

    @Override
    public Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients) {
        return recipeRepository.addIngredients(recipe, ingredients);
    }

    @Override
    public void removeIngredients(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        recipeRepository.removeIngredients(recipe, ingredients);
    }

//    @Override
//    public Ingredient getIngredientFromRecipe(Long recipeId, String ingredientName) {
//
//        Recipe recipe = getRecipe(recipeId);
//        Ingredient ingredient = null;
//
//        List<Ingredient> ingredients = recipe.getIngredients();
//        for (int x = 0; x < ingredients.size(); x++) {
//            if (ingredientName == ingredients.get(x).getName()) {
//                ingredient = ingredients.get(x);
//                break;
//            }
//        }
//
//        return ingredient;
//    }
}
