package org.example.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.data.PantryManager;
import org.example.data.RecipeManager;
import org.example.utils.ShoppingListCalculator;
import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.util.List;
import java.util.Optional;

public class MainUI extends Application {
    private PantryManager pantryManager = new PantryManager();
    private RecipeManager recipeManager = new RecipeManager();
    private ShoppingListCalculator shoppingListCalculator = new ShoppingListCalculator();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cooking App");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        // Display welcome message and instructions
        Label welcomeLabel = new Label("Hi, here's how you use the app:");
        welcomeLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        Label step1 = new Label("1. Add items you have in stock.");
        Label step2 = new Label("2. Choose your recipe.");
        Label step3 = new Label("3. Calculate what is needed to be bought.");
        step1.setStyle("-fx-font-size: 14;");
        step2.setStyle("-fx-font-size: 14;");
        step3.setStyle("-fx-font-size: 14;");

        // Start button to proceed to the main menu
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        startButton.setOnAction(e -> showMainMenu(primaryStage));

        layout.getChildren().addAll(welcomeLabel, step1, step2, step3, startButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display the main menu
    private void showMainMenu(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        // Button to add an ingredient to the pantry
        Button addIngredientButton = new Button("Add Ingredient to Pantry");
        addIngredientButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        addIngredientButton.setOnAction(e -> new AddIngredientUI().start(new Stage()));

        // Button to show the pantry
        Button showPantryButton = new Button("Show Pantry");
        showPantryButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        showPantryButton.setOnAction(e -> new PantryUI().start(new Stage()));

        // Button to view recipes
        Button viewRecipesButton = new Button("View Recipes");
        viewRecipesButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        viewRecipesButton.setOnAction(e -> new RecipesUI().start(new Stage()));

        // Button to calculate the shopping list
        Button calculateShoppingListButton = new Button("Calculate Shopping List");
        calculateShoppingListButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        calculateShoppingListButton.setOnAction(e -> calculateAndDisplayShoppingList(primaryStage));

        layout.getChildren().addAll(addIngredientButton, showPantryButton, viewRecipesButton, calculateShoppingListButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to calculate and display the shopping list
    private void calculateAndDisplayShoppingList(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Calculate Shopping List");
        dialog.setHeaderText("Enter the name of the recipe:");
        dialog.setContentText("Recipe name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(recipeName -> {
            Recipe recipe = recipeManager.getRecipes().stream()
                    .filter(r -> r.getName().equalsIgnoreCase(recipeName))
                    .findFirst()
                    .orElse(null);

            if (recipe == null) {
                showAlert(AlertType.ERROR, "Recipe not found.");
                return;
            }

            List<Ingredient> missingIngredients = shoppingListCalculator.calculateShoppingList(recipe, pantryManager.getPantry());
            if (missingIngredients.isEmpty()) {
                showAlert(AlertType.INFORMATION, "You have all the ingredients for the recipe.");
                showRecipeDetails(primaryStage, recipe);
            } else {
                StringBuilder missingIngredientsText = new StringBuilder("You are missing the following ingredients:\n");
                for (Ingredient ingredient : missingIngredients) {
                    missingIngredientsText.append(ingredient.getName()).append(": ").append(ingredient.getQuantity()).append("\n");
                }
                showAlert(AlertType.INFORMATION, missingIngredientsText.toString());
                showRecipeDetails(primaryStage, recipe);
            }
        });
    }

    // Method to display the details of a recipe
    private void showRecipeDetails(Stage primaryStage, Recipe recipe) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        Label recipeLabel = new Label(recipe.getName());
        recipeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #333;");
        layout.getChildren().add(recipeLabel);

        for (Ingredient ingredient : recipe.getIngredients()) {
            Label ingredientLabel = new Label("  " + ingredient.getName() + ": " + ingredient.getQuantity());
            ingredientLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #555;");
            layout.getChildren().add(ingredientLabel);
        }

        Label instructionsLabel = new Label("Instructions: Mix all ingredients and bake at 350°F for 30 minutes.");
        instructionsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #777;");
        layout.getChildren().add(instructionsLabel);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white;");
        backButton.setOnAction(e -> showMainMenu(primaryStage));
        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to show an alert dialog
    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
