package com.application.app.fridge;

import com.application.app.ingredient.Ingredient;
import com.application.app.ingredient.IngredientRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FridgeServiceInterface {

    void createFridge(Long id);

    Fridge getFridge(Long fridgeId);

    Fridge updateFridge(Long fridgeId, List<IngredientRequest> ingredientRequests);

    Fridge addIngredients(Fridge fridge, List<Ingredient> ingredients);

    void removeIngredients(Fridge fridge);
}
