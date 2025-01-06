package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.data.PantryManager;
import org.example.models.Ingredient;

/**
 * The PantryUI class is responsible for displaying the pantry items in a JavaFX application.
 */
public class PantryUI extends Application {
    private PantryManager pantryManager = new PantryManager();

    /**
     * The start method is the main entry point for the JavaFX application.
     * It sets up the primary stage and displays the pantry items.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pantry");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #f0f0f0;");

        try {
            // Loop through each ingredient in the pantry and display its details
            for (Ingredient ingredient : pantryManager.getPantry()) {
                Label ingredientLabel = new Label(ingredient.getName() + ": " + ingredient.getQuantity());
                ingredientLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #555;");
                layout.getChildren().add(ingredientLabel);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error loading pantry: " + e.getMessage());
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
     * Method to show an alert with a given message.
     *
     * @param alertType the type of alert to be displayed
     * @param message the message to be displayed in the alert
     */
    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
