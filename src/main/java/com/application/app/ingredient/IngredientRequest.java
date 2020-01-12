package com.application.app.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequest {
    private Long id;
    private String ingredientName;
    private String amount;
}
