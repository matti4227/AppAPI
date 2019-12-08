package com.application.app.cookbook;

import com.application.app.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cookbook")
public class CookbookController {

    @Autowired
    private CookbookService cookbookService;

    @GetMapping(value = "")
    public ResponseEntity<Cookbook> getCookbook(@RequestBody CookbookRequest cookbook) {
        try {
            Cookbook response = cookbookService.getCookbook(cookbook.getCookbookId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{recipeId}")
    public ResponseEntity<Recipe> getRecipeFromCookbook(@PathVariable Long recipeId,
                                                        @RequestBody CookbookRequest cookbookRequest) {
        try {
            Recipe response = cookbookService.getRecipeFromCookbook(cookbookRequest.getCookbookId(), recipeId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Cookbook> removeRecipe(@RequestBody CookbookRecipeRequest cookbookRecipeRequest ) {
        try {
            Cookbook response = cookbookService.removeRecipe(cookbookRecipeRequest.getRecipeId(), cookbookRecipeRequest.getCookbookId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
