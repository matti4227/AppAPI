package com.application.app.cookbook;

import com.application.app.recipe.Recipe;
import com.application.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cookbook")
public class CookbookController {

    @Autowired
    private CookbookService cookbookService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "")
    public ResponseEntity<Cookbook> getCookbook(@RequestBody CookbookRequest cookbook) {
        try {
            if (securityService.isSecuredGetCookbook(cookbook.getCookbookId()) == true) {
                Cookbook response = cookbookService.getCookbook(cookbook.getCookbookId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{recipeId}")
    public ResponseEntity<Recipe> getRecipeFromCookbook(@PathVariable Long recipeId) {
        try {
            Recipe response = cookbookService.getRecipeFromCookbook(recipeId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Cookbook> removeRecipe(@RequestBody CookbookRecipeRequest cookbookRecipeRequest ) {
        try {
            if (securityService.isSecuredRemoveRecipeFromCookbook(cookbookRecipeRequest.getCookbookId()) == true) {
                Cookbook response = cookbookService.removeRecipe(cookbookRecipeRequest.getRecipeId(), cookbookRecipeRequest.getCookbookId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
