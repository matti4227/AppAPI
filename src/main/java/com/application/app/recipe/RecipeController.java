package com.application.app.recipe;

import com.application.app.cookbook.CookbookService;
import com.application.app.ingredient.IngredientRequest;
import com.application.app.recipe.comment.RecipeCommentRequest;
import com.application.app.recipe.vote.RecipeVoteRequest;
import com.application.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SecurityService securityService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
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
    public ResponseEntity<RecipePageResponse> getRecipesByParameters(
            @RequestBody List<IngredientRequest> ingredientRequestList,
            @RequestParam(value = "category", defaultValue = "") String categoryName,
            @RequestParam(value = "diff", defaultValue = "0") int difficulty,
            @RequestParam(value = "prepTime", defaultValue = "0") int preparationTime,
            @RequestParam(value = "sort", defaultValue = "0") int sort,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try {
            RecipePageResponse response = recipeService.getRecipesByParameters(ingredientRequestList, categoryName, difficulty, preparationTime, sort, page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<RecipePageResponse> getRecipesByNameSearch(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try {
            RecipePageResponse response = recipeService.getRecipesByNameSearch(name, page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/search/a")
    public ResponseEntity<RecipePageResponse> getRecipesByIngredients(
            @RequestBody List<IngredientRequest> ingredientRequestList,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try {
            RecipePageResponse response = recipeService.getRecipesByIngredients(ingredientRequestList, page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeRequest recipeRequest) {
        try {
            if (securityService.isSecuredUpdateRecipe(id) == true) {
                Recipe response = recipeService.updateRecipe(id, recipeRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable(value = "id") Long id) {
        try {
            if (securityService.isSecuredDeleteRecipe(id) == true) {
                recipeService.deleteRecipe(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> addToCookbook(@PathVariable(value = "id") Long recipeId,
                                                @RequestBody RecipeCookbookRequest recipeCookbookRequest) {
        try {
            if (securityService.isSecuredAddRecipeToCookbook(recipeCookbookRequest.getId()) == true) {
                recipeService.addRecipeToCookbook(recipeId, recipeCookbookRequest.getId());
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> rateRecipe(@PathVariable(value = "id") Long recipeId,
                                             @RequestBody RecipeVoteRequest recipeVoteRequest) {
        try {
            recipeService.addScoreToRecipe(recipeId, recipeVoteRequest);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> commentRecipe(@PathVariable(value = "id") Long recipeId,
                                                @RequestBody RecipeCommentRequest recipeCommentRequest) {
        try {
            recipeService.addCommentToRecipe(recipeId, recipeCommentRequest);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
