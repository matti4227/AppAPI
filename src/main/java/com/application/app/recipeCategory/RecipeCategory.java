//package com.application.app.recipeCategory;
//
//import com.application.app.recipe.Recipe;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "recipe_category")
//public class RecipeCategory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    private String name;
//
//    @JsonBackReference
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "recipe_category",
//            joinColumns = @JoinColumn(name = "id_recipe_category"),
//            inverseJoinColumns = @JoinColumn(name = "id_recipe")
//    )
//    private List<Recipe> recipes = new ArrayList<>();
//}
