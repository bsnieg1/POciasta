package org.example.data;

import org.example.models.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PantryManager {
    private static final String DB_URL = "jdbc:sqlite:database2.db";

    public PantryManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS pantry (name TEXT, quantity REAL)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to add an ingredient to the pantry database
    public void addIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO pantry(name, quantity) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredient.getName());
            pstmt.setDouble(2, ingredient.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to retrieve the list of ingredients from the pantry database
    public List<Ingredient> getPantry() {
        List<Ingredient> pantry = new ArrayList<>();
        String sql = "SELECT name, quantity FROM pantry";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pantry.add(new Ingredient(rs.getString("name"), rs.getDouble("quantity")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pantry;
    }
}
