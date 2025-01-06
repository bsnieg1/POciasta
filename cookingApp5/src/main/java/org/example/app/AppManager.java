package org.example.app;

import org.example.data.PantryManager;
import org.example.data.RecipeManager;
import org.example.ui.MainUI;
import org.example.utils.ShoppingListCalculator;
import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private PantryManager pantryManager;
    private RecipeManager recipeManager;
    private ShoppingListCalculator shoppingListCalculator;

    public AppManager() {
        pantryManager = new PantryManager();
        recipeManager = new RecipeManager();
        shoppingListCalculator = new ShoppingListCalculator();
        hardcodeRecipes();
    }

    public void run() {
        // Initialize JavaFX UI
        MainUI.main(new String[]{});
    }

    private void hardcodeRecipes() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2.0));
        ingredients.add(new Ingredient("Sugar", 1.0));
        ingredients.add(new Ingredient("Eggs", 3.0));
        recipeManager.addRecipe(new Recipe("Cake", ingredients));
    }
}