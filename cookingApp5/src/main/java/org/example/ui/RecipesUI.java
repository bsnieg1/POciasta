package org.example.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.data.RecipeManager;
import org.example.models.Ingredient;
import org.example.models.Recipe;

/**
 * The RecipesUI class is a JavaFX application that displays a list of recipes.
 * Each recipe includes its name, ingredients, and instructions.
 * The user can navigate back to the previous screen using a back button.
 */
public class RecipesUI extends Application {
    private RecipeManager recipeManager = new RecipeManager();

    /**
     * The start method is the main entry point for the JavaFX application.
     * It sets up the primary stage and displays the list of recipes.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Recipes");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        try {
            // Loop through each recipe and display its details
            for (Recipe recipe : recipeManager.getRecipes()) {
                Label recipeLabel = new Label(recipe.getName());
                recipeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #333;");
                layout.getChildren().add(recipeLabel);

                // Display each ingredient of the recipe
                for (Ingredient ingredient : recipe.getIngredients()) {
                    Label ingredientLabel = new Label("  " + ingredient.getName() + ": " + ingredient.getQuantity());
                    ingredientLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #555;");
                    layout.getChildren().add(ingredientLabel);
                }

                // Display the instructions for the recipe
                Label instructionsLabel = new Label("Instructions: Mix all ingredients and bake at 350°F for 30 minutes.");
                instructionsLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #777;");
                layout.getChildren().add(instructionsLabel);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error loading recipes: " + e.getMessage());
        }

        // Add a back button to return to the previous screen
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white;");
        backButton.setOnAction(e -> primaryStage.close());
        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The showAlert method displays an alert with a given message.
     *
     * @param alertType the type of alert to display
     * @param message   the message to display in the alert
     */
    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
