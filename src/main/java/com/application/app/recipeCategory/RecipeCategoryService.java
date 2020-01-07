//package com.application.app.recipeCategory;
//
//import com.application.app.recipe.Recipe;
//import com.application.app.recipe.RecipeRepositoryInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RecipeCategoryService implements RecipeCategoryServiceInterface {
//
//    @Autowired
//    private RecipeCategoryRepositoryInterface recipeCategoryRepositoryInterface;
//
//    @Autowired
//    private RecipeRepositoryInterface recipeRepositoryInterface;
//
//    @Autowired
//    private RecipeCategoryRepository recipeCategoryRepository;
//
//    @Override
//    public List<RecipeCategory> getCategories() {
//        return recipeCategoryRepositoryInterface.findAll();
//    }
//
//    @Override
//    public RecipeCategory getCategory(String categoryName) {
//        return recipeCategoryRepositoryInterface.findByName(categoryName);
//    }
//
//    @Override
//    public RecipeCategory addCategory(String name) {
//        RecipeCategory recipeCategory = new RecipeCategory();
//        recipeCategory.setName(name);
//        return recipeCategoryRepositoryInterface.save(recipeCategory);
//    }
//
//    @Override
//    public RecipeCategory addRecipeToCategory(String categoryName, Long recipeId) {
//        Recipe recipe = recipeRepositoryInterface.findById(recipeId).orElse(null);
//        RecipeCategory recipeCategory = getCategory(categoryName);
//
//        recipeCategory = recipeCategoryRepository.addRecipe(recipeCategory, recipe);
//        return recipeCategoryRepositoryInterface.save(recipeCategory);
//    }
//
//    @Override
//    public RecipeCategory removeRecipeFromCategory(String categoryName, Long recipeId) {
//        Recipe recipe = getRecipeFromRecipeCategory(categoryName, recipeId);
//        RecipeCategory recipeCategory = getCategory(categoryName);
//
//        recipeCategory = recipeCategoryRepository.removeRecipe(recipeCategory, recipe);
//        return recipeCategoryRepositoryInterface.save(recipeCategory);
//    }
//
//    @Override
//    public Recipe getRecipeFromRecipeCategory(String categoryName, Long recipeId) {
//        RecipeCategory recipeCategory = getCategory(categoryName);
//        Recipe recipe = null;
//
//        List<Recipe> recipes = recipeCategory.getRecipes();
//        for (int x = 0; x < recipes.size(); x++) {
//            if (recipeId == recipes.get(x).getId()) {
//                recipe = recipes.get(x);
//                break;
//            }
//        }
//        return recipe;
//    }
//}
