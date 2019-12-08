package com.application.app.recipe;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository(value = "recipeRepository")
public class RecipeRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Recipe createRecipe(RecipeRequest recipeRequest, ApplicationUser user) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setPreparation(recipeRequest.getPreparation());
        recipe.setDifficulty(recipeRequest.getDifficulty());
        recipe.setPreparationTime(recipeRequest.getPreparationTime());
        recipe.setUser(user);

        return recipe;
    }

    public Recipe updateRecipe(Recipe originalRecipe, RecipeRequest recipe) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (recipe.getName() != null) {
            originalRecipe.setName(recipe.getName());
        }
        if (recipe.getDescription() != null) {
            originalRecipe.setDescription(recipe.getDescription());
        }
        if (recipe.getPreparation() != null) {
            originalRecipe.setPreparation(recipe.getDescription());
        }
        originalRecipe.setDifficulty(recipe.getDifficulty());
        originalRecipe.setPreparationTime(recipe.getDifficulty());

        entityManager.merge(originalRecipe);
        entityManager.getTransaction().commit();

        return originalRecipe;
    }

    public Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (int x = 0; x < ingredients.size(); x++) {
            recipe.getIngredients().add(ingredients.get(x));
            ingredients.get(x).getRecipes().add(recipe);
        }

        entityManager.merge(recipe);
        entityManager.getTransaction().commit();

        return recipe;
    }

    public Recipe removeIngredients(Recipe recipe, List<Ingredient> ingredients) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (int x = 0; x < ingredients.size(); x++) {
            ingredients.get(x).getRecipes().remove(recipe);
        }

        recipe.getIngredients().clear();

        entityManager.merge(recipe);
        entityManager.getTransaction().commit();

        return recipe;
    }
}
