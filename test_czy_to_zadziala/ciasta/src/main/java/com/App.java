package com;

import java.io.IOException;

import com.model.RecipeObject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

public class App extends Application {

    static Scene scene;
    private static RecipeObject pickedRecipe;

    public static void setPickedRecipe(RecipeObject recipe) {
        pickedRecipe = recipe;
    }

    public static RecipeObject getPickedRecipe() {
        return pickedRecipe;
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Starting the application...");
        scene = new Scene(loadFXML("primary"), 700, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
