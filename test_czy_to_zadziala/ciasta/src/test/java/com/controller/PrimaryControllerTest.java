package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PrimaryControllerTest {

    private PrimaryController controller;

    @BeforeEach
    public void setUp() {
        controller = new PrimaryController();
        controller.firstIngredient = new Label();
        controller.secondIngredient = new Label();
        controller.thirdIngredient = new Label();
        controller.fourthIngredient = new Label();
        controller.fifthIngredient = new Label();
        controller.sixthIngredient = new Label();
        controller.seventhIngredient = new Label();
        controller.eighthIngredient = new Label();
        controller.time = new Label();
        controller.whatRecipe = new Label();
        controller.actualRecipe = new Label();
        controller.portions = new Label();
        controller.difficulty = new Label();
        controller.makeCake = new Button();
        controller.boxForProduct = new VBox();
        controller.boxForProductPantry = new VBox();
        controller.timerLabel = new Label();
    }

    @Test
    public void testInitializeRecipe() {
        controller.pickedRecipe = new RecipeObject("Test Recipe", "ingredient1 100g,ingredient2 200g", "Step 1\nStep 2", 30, 4, "easy");
        controller.initializeRecipe();
        assertEquals("ingredient1", controller.firstIngredient.getText());
        assertEquals("ingredient2", controller.secondIngredient.getText());
        assertEquals("30 minut", controller.time.getText());
        assertEquals("Test Recipe", controller.whatRecipe.getText());
        assertEquals("Step 1\nStep 2", controller.actualRecipe.getText());
        assertEquals("4", controller.portions.getText());
        assertEquals("easy", controller.difficulty.getText());
    }

    @Test
    public void testAddFiveMinutes() {
        controller.addFiveMinutes();
        assertEquals("05:00", controller.timerLabel.getText());
    }

    @Test
    public void testSubtractFiveMinutes() {
        controller.addFiveMinutes();
        controller.subtractFiveMinutes();
        assertEquals("00:00", controller.timerLabel.getText());
    }

    @Test
    public void testStartStopTimer() throws InterruptedException {
        controller.addFiveMinutes();
        controller.startTimer();
        Thread.sleep(2000);
        controller.stopTimer();
        String timerText = controller.timerLabel.getText();
        assertTrue(timerText.equals("04:58") || timerText.equals("04:59"));
    }
}
