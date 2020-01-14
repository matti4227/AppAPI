package com.application.app.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
        try {
            Ingredient response = ingredientService.addIngredient(ingredientRequest.getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping(value = "")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        try {
            List<Ingredient> response = ingredientService.getAllIngredients();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "")
    public ResponseEntity<Object> deleteIngredient(@RequestBody IngredientRequest ingredientRequest) {
        try {
            ingredientService.deleteIngredient(ingredientRequest.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
