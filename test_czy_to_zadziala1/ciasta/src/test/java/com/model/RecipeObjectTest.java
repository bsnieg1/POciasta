package com.model;

import com.database.Pantry; // Add this import statement
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeObjectTest {

    @Test
    public void testGetName() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertEquals("Test Recipe", recipe.getName());
    }

    @Test
    public void testGetIngredients() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        String[] ingredients = recipe.getIngredients();
        assertArrayEquals(new String[]{"ingredient1", "ingredient2"}, ingredients);
    }

    @Test
    public void testGetIngredient() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertEquals("ingredient1", recipe.getIngredient(0));
        assertEquals("ingredient2", recipe.getIngredient(1));
    }

    @Test
    public void testGetPreparationTime() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertEquals(30, recipe.getPreparationTime());
    }

    @Test
    public void testGetServings() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertEquals(4, recipe.getServings());
    }

    @Test
    public void testGetDifficulty() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertEquals("easy", recipe.getDiff());
    }
    @Test
    public void testSetName() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setName("New Recipe Name");
        assertEquals("New Recipe Name", recipe.getName());
    }

    @Test
    public void testSetIngredients() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setIngredients(new String[]{"newIngredient1", "newIngredient2"});
        String[] ingredients = recipe.getIngredients();
        assertArrayEquals(new String[]{"newIngredient1", "newIngredient2"}, ingredients);
    }

    @Test
    public void testSetInstructions() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setInstructions("new instructions");
        assertEquals("new instructions", recipe.getInstructions());
    }

    @Test
    public void testSetPreparationTime() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setPreparationTime(45);
        assertEquals(45, recipe.getPreparationTime());
    }

    @Test
    public void testSetServings() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setServings(6);
        assertEquals(6, recipe.getServings());
    }

    @Test
    public void testSetDifficulty() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        recipe.setDiff("medium");
        assertEquals("medium", recipe.getDiff());
    }

    @Test
    public void testCheckPantryforRecipe() {
        // Mock the pantry data
        String[][] mockPantryData = {
            {"1", "ingredient1", "100"},
            {"2", "ingredient2", "200"}
        };
        Pantry.setMockData(mockPantryData); // Assuming Pantry has a method to set mock data for testing

        String[] ingredients = {"ingredient1 100g", "ingredient2 200g"};
        assertTrue(RecipeObject.checkPantryforRecipe(ingredients));
    }

    @Test
    public void testCheckPantryforRecipeInsufficientIngredients() {
        // Mock the pantry data
        String[][] mockPantryData = {
            {"1", "ingredient1", "100"},
            {"2", "ingredient2", "200"}
        };
        Pantry.setMockData(mockPantryData); // Assuming Pantry has a method to set mock data for testing

        String[] ingredients = {"ingredient1 1000g", "ingredient2 2000g"};
        assertFalse(RecipeObject.checkPantryforRecipe(ingredients));
    }

    @Test
    public void testGetIngredientInvalidIndex() {
        RecipeObject recipe = new RecipeObject("Test Recipe", "ingredient1,ingredient2", "instructions", 30, 4, "easy");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            recipe.getIngredient(5);
        });
    }
}