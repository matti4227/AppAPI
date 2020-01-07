//package com.application.app.recipeCategory;
//
//import com.application.app.recipe.Recipe;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//@Repository(value = "recipeCategoryRepository")
//public class RecipeCategoryRepository {
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    public RecipeCategory addRecipe(RecipeCategory recipeCategory, Recipe recipe) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        recipeCategory.getRecipes().add(recipe);
//        recipe.getRecipeCategories().add(recipeCategory);
//
//        entityManager.merge(recipeCategory);
//        entityManager.getTransaction().commit();
//
//        return recipeCategory;
//    }
//
//    public RecipeCategory removeRecipe(RecipeCategory recipeCategory, Recipe recipe) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        recipeCategory.getRecipes().remove(recipe);
//        recipe.getRecipeCategories().remove(recipeCategory);
//
//
//        entityManager.merge(recipeCategory);
//        entityManager.getTransaction().commit();
//
//        return recipeCategory;
//    }
//}
