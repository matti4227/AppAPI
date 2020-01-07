//package com.application.app.recipeCategory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/recipeCategories")
//public class RecipeCategoryController {
//
//    @Autowired
//    private RecipeCategoryService recipeCategoryService;
//
//    @PostMapping
//    public ResponseEntity<RecipeCategory> createRecipeCategory(@RequestBody RecipeCategoryRequest recipeCategoryRequest ) {
//        try {
//            RecipeCategory response = recipeCategoryService.addCategory(recipeCategoryRequest.getName());
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception er) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
