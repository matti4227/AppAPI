//package com.application.app.recipe.recipeCategory;
//
//import com.application.app.recipe.Recipe;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "recipecategory")
//public class RecipeCategory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
////    @JsonBackReference
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn
////    private Ingredient ingredient;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private Recipe recipe;
//}
