package com.application.app.recipeCategory;

import com.application.app.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipeCategories")
public class RecipeCategoryController {

    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @PostMapping
    public ResponseEntity<RecipeCategory> createRecipeCategory(@RequestBody RecipeCategoryRequest recipeCategoryRequest ) {
        try {
            RecipeCategory response = recipeCategoryService.addCategory(recipeCategoryRequest.getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<RecipeCategory>> getAllCategories() {
        try {
            List<RecipeCategory> response = recipeCategoryService.getAllCategories();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Recipe>> getCategory(@RequestBody RecipeCategoryRequest recipeCategoryRequest) {
        try {
            List<Recipe> response = recipeCategoryService.getRecipesByCategory(recipeCategoryRequest.getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
