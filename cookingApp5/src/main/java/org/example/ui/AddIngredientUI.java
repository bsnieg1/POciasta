package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.data.PantryManager;
import org.example.models.Ingredient;

public class AddIngredientUI extends Application {
    private PantryManager pantryManager = new PantryManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Ingredient");

        Label nameLabel = new Label("Ingredient Name:");
        TextField nameField = new TextField();

        Label quantityLabel = new Label("Ingredient Quantity:");
        TextField quantityField = new TextField();

        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                double quantity = Double.parseDouble(quantityField.getText());
                pantryManager.addIngredient(new Ingredient(name, quantity));
                primaryStage.close();
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Invalid quantity. Please enter a valid number.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white;");
        backButton.setOnAction(e -> primaryStage.close());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, backButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #f0f0f0;");
        layout.getChildren().addAll(nameLabel, nameField, quantityLabel, quantityField, buttonBox);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to show an alert with a given message
    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
