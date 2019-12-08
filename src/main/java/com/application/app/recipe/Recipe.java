package com.application.app.recipe;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.cookbook.Cookbook;
import com.application.app.ingredient.Ingredient;
import com.application.app.recipeCategory.RecipeCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotBlank
    @Size(max = 50000)
    private String preparation;

    @Min(1)
    @Column(name = "preparation_time")
    private int preparationTime;

    @Min(1)
    @Max(5)
    private int difficulty;

    private float rating;

    @Lob
    private byte[] picture;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ApplicationUser user;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cookbook_recipe",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_cookbook")
    )
    private List<Cookbook> cookbooks = new ArrayList<>();

//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.LAZY)
//    private List<RecipePicture> recipePictures = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private List<Ingredient> ingredients = new ArrayList<>();
}
