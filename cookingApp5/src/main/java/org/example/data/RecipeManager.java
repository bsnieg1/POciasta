package org.example.data;

import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private static final String DB_URL = "jdbc:sqlite:database1.db";

    public RecipeManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS recipes (name TEXT)");
                stmt.execute("CREATE TABLE IF NOT EXISTS ingredients (recipe_name TEXT, name TEXT, quantity REAL)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to add a recipe to the database
    public void addRecipe(Recipe recipe) {
        String sqlRecipe = "INSERT INTO recipes(name) VALUES(?)";
        String sqlIngredient = "INSERT INTO ingredients(recipe_name, name, quantity) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmtRecipe = conn.prepareStatement(sqlRecipe);
             PreparedStatement pstmtIngredient = conn.prepareStatement(sqlIngredient)) {

            pstmtRecipe.setString(1, recipe.getName());
            pstmtRecipe.executeUpdate();

            for (Ingredient ingredient : recipe.getIngredients()) {
                pstmtIngredient.setString(1, recipe.getName());
                pstmtIngredient.setString(2, ingredient.getName());
                pstmtIngredient.setDouble(3, ingredient.getQuantity());
                pstmtIngredient.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get all recipes from the database
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sqlRecipes = "SELECT name FROM recipes";
        String sqlIngredients = "SELECT name, quantity FROM ingredients WHERE recipe_name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmtRecipes = conn.createStatement();
             ResultSet rsRecipes = stmtRecipes.executeQuery(sqlRecipes)) {

            while (rsRecipes.next()) {
                String recipeName = rsRecipes.getString("name");
                List<Ingredient> ingredients = new ArrayList<>();

                try (PreparedStatement pstmtIngredients = conn.prepareStatement(sqlIngredients)) {
                    pstmtIngredients.setString(1, recipeName);
                    ResultSet rsIngredients = pstmtIngredients.executeQuery();

                    while (rsIngredients.next()) {
                        ingredients.add(new Ingredient(rsIngredients.getString("name"), rsIngredients.getDouble("quantity")));
                    }
                }

                recipes.add(new Recipe(recipeName, ingredients));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recipes;
    }
}
