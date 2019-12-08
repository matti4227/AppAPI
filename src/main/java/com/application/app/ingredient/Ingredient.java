package com.application.app.ingredient;

import com.application.app.fridge.Fridge;
import com.application.app.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe")
    )
    private List<Recipe> recipes = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fridge_ingredient",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_fridge")
    )
    private List<Fridge> fridges = new ArrayList<>();
}
