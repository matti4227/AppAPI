package com.application.app.recipe;

import com.application.app.cookbook.CookbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CookbookService cookbookService;

    @PostMapping(value = "/create")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeRequest recipeRequest) {
        try {
            Recipe response = recipeService.createRecipe(recipeRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable(value = "id") Long id) {
        try {
            Recipe response = recipeService.getRecipe(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Recipe>> getRecipes() {
        try {
            List<Recipe> response = recipeService.getRecipes();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeRequest recipeRequest) {
        try {
            Recipe response = recipeService.updateRecipe(id, recipeRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable(value = "id") Long id) {
        try {
            recipeService.deleteRecipe(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> addToCookbook(@PathVariable(value = "id") Long recipeId,
                                                @RequestBody RecipeCookbookRequest recipeCookbookRequest) {
        try {
            cookbookService.addRecipe(recipeId, recipeCookbookRequest.getId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
