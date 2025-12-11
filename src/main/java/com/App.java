package com;

import java.io.IOException;

import com.model.RecipeObject;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

public class App extends Application {

    static Scene scene;
    private static RecipeObject pickedRecipe;
    private static int timeInSeconds = 0;
    private static Timeline timeline;

    public static void setPickedRecipe(RecipeObject recipe) {
        pickedRecipe = recipe;
    }

    public static RecipeObject getPickedRecipe() {
        return pickedRecipe;
    }

    public static int getTimeInSeconds() {
        return timeInSeconds;
    }

    public static void setTimeInSeconds(int time) {
        timeInSeconds = time;
    }

    public static Timeline getTimeline() {
        return timeline;
    }

    public static void setTimeline(Timeline newTimeline) {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = newTimeline;
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
        if (fxml.equals("recipe") && timeline != null) {
            timeline.play();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
