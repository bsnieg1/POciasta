package org.example.app;

import javafx.scene.Scene;
import org.example.data.PantryManager;
import org.example.data.RecipeManager;
import org.example.data.DataManager;
import org.example.ui.ConsoleUI;
import org.example.utils.ShoppingListCalculator;
import org.example.models.Ingredient;
import org.example.models.Recipe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private static final String PANTRY_FILE = "pantry.dat";
    private static final String RECIPES_FILE = "recipes.dat";
    static Scene scene;


    private PantryManager pantryManager;
    private RecipeManager recipeManager;
    private ConsoleUI consoleUI;
    private ShoppingListCalculator shoppingListCalculator;

    public AppManager() {
        pantryManager = new PantryManager();
        recipeManager = new RecipeManager();
        // consoleUI = new ConsoleUI();
        shoppingListCalculator = new ShoppingListCalculator();
        loadData();
    }

    public void run() {
        scene = new Scene(loadFXML("primary"), 700, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                pantryManager.addIngredient(consoleUI.getIngredientInput());
                break;
            case 2:
                recipeManager.addRecipe(consoleUI.getRecipeInput());
                break;
            case 3:
                calculateAndDisplayShoppingList();
                break;
            case 4:
                editIngredientInPantry();
                break;
            case 5:
                removeIngredientFromPantry();
                break;
            case 6:
                editRecipe();
                break;
            case 7:
                removeRecipe();
                break;
            case 8:
                saveData();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void calculateAndDisplayShoppingList() {
        System.out.print("Enter the name of the recipe: ");
        String recipeName = consoleUI.getScanner().next();
        Recipe recipe = recipeManager.getRecipes().stream()
                .filter(r -> r.getName().equalsIgnoreCase(recipeName))
                .findFirst()
                .orElse(null);

        if (recipe == null) {
            System.out.println("Recipe not found.");
            return;
        }

        List<Ingredient> missingIngredients = shoppingListCalculator.calculateShoppingList(recipe, pantryManager.getPantry());
        if (missingIngredients.isEmpty()) {
            System.out.println("You have all the ingredients for the recipe.");
        } else {
            System.out.println("You are missing the following ingredients:");
            for (Ingredient ingredient : missingIngredients) {
                System.out.println(ingredient.getName() + ": " + ingredient.getQuantity());
            }
        }
    }

    private void editIngredientInPantry() {
        String name = consoleUI.getIngredientName();
        Ingredient ingredient = pantryManager.getPantry().stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (ingredient == null) {
            System.out.println("Ingredient not found.");
            return;
        }

        System.out.print("Enter new quantity: ");
        while (!consoleUI.getScanner().hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            consoleUI.getScanner().next(); // Clear invalid input
        }
        double quantity = consoleUI.getScanner().nextDouble();
        ingredient.setQuantity(quantity);
    }

    private void removeIngredientFromPantry() {
        String name = consoleUI.getIngredientName();
        pantryManager.getPantry().removeIf(i -> i.getName().equalsIgnoreCase(name));
    }

    private void editRecipe() {
        String name = consoleUI.getRecipeName();
        Recipe recipe = recipeManager.getRecipes().stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (recipe == null) {
            System.out.println("Recipe not found.");
            return;
        }

        System.out.print("Enter new number of ingredients: ");
        while (!consoleUI.getScanner().hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            consoleUI.getScanner().next(); // Clear invalid input
        }
        int numIngredients = consoleUI.getScanner().nextInt();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            ingredients.add(consoleUI.getIngredientInput());
        }
        recipe.setIngredients(ingredients);
    }

    private void removeRecipe() {
        String name = consoleUI.getRecipeName();
        recipeManager.getRecipes().removeIf(r -> r.getName().equalsIgnoreCase(name));
    }

    private void saveData() {
        try {
            DataManager.savePantry(pantryManager.getPantry(), PANTRY_FILE);
            DataManager.saveRecipes(recipeManager.getRecipes(), RECIPES_FILE);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            List<Ingredient> pantry = DataManager.loadPantry(PANTRY_FILE);
            pantryManager.getPantry().addAll(pantry);
            List<Recipe> recipes = DataManager.loadRecipes(RECIPES_FILE);
            recipeManager.getRecipes().addAll(recipes);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}