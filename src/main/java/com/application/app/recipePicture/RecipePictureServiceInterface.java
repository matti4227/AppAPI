package com.application.app.recipePicture;

import com.application.app.recipe.Recipe;
import org.springframework.stereotype.Service;

@Service
public interface RecipePictureServiceInterface {
    void addPictureToRecipe(Recipe recipe, byte[] picture);
    void removePictureFromRecipe(Recipe recipe, Long pictureId);
}
