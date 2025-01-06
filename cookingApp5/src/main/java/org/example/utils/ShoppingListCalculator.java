package org.example.utils;

import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.util.List;
import java.util.ArrayList;

public class ShoppingListCalculator {
    public static List<Ingredient> calculateShoppingList(Recipe recipe, List<Ingredient> pantry) {
        List<Ingredient> missingIngredients = new ArrayList<>();

        for (Ingredient recipeIngredient : recipe.getIngredients()) {
            boolean found = false;

            for (Ingredient pantryIngredient : pantry) {
                if (recipeIngredient.getName().equalsIgnoreCase(pantryIngredient.getName())) {
                    found = true;
                    if (pantryIngredient.getQuantity() < recipeIngredient.getQuantity()) {
                        missingIngredients.add(new Ingredient(
                            recipeIngredient.getName(),
                            recipeIngredient.getQuantity() - pantryIngredient.getQuantity()
                        ));
                    }
                    break;
                }
            }

            if (!found) {
                missingIngredients.add(recipeIngredient);
            }
        }

        return missingIngredients;
    }
}
