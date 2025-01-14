package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Pantry {

    private static final String URL = "jdbc:sqlite:C:\\Users\\barto\\OneDrive\\Pulpit\\test_czy_to_zadziala\\ciasta\\src\\main\\resources\\com\\Pantry.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Connection to second database failed: " + e.getMessage());
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "product_name TEXT NOT NULL, "
                   + "product_amount INTEGER NOT NULL)";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);  
            System.out.println("Table 'products' created successfully in the second database.");
        } catch (SQLException e) {
            System.out.println("Error while creating the table in the second database: " + e.getMessage());
        }
    }

    public static void insertOrUpdateProduct(String productName, int productAmount) {
        String selectSql = "SELECT product_amount FROM products WHERE product_name = ?";
        String updateSql = "UPDATE products SET product_amount = product_amount + ? WHERE product_name = ?";
        String insertSql = "INSERT INTO products(product_name, product_amount) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setString(1, productName);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, productAmount);
                    updateStmt.setString(2, productName);
                    updateStmt.executeUpdate();
                    System.out.println("Product updated in the second database: " + productName + " - new amount added: " + productAmount);
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, productName);
                    insertStmt.setInt(2, productAmount);
                    insertStmt.executeUpdate();
                    System.out.println("Product added to the second database: " + productName + " - amount: " + productAmount);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting or updating the product in the second database: " + e.getMessage());
        }
    }

    public static ResultSet getProducts() {
        String sql = "SELECT * FROM products";
        
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();  
        } catch (SQLException e) {
            System.out.println("Error while retrieving products from the second database: " + e.getMessage());
            return null;
        }
    }

    public static void deleteProduct(String productName) {
        String sql = "DELETE FROM products WHERE product_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);  
            stmt.executeUpdate();            
            System.out.println("Product deleted from the second database: " + productName);
        } catch (SQLException e) {
            System.out.println("Error while deleting the product from the second database: " + e.getMessage());
        }
    }

    public static void clearDatabase2() {
        String sql = "DELETE FROM products";
    
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();  
            System.out.println("All data has been cleared from the database.");
        } catch (SQLException e) {
            System.out.println("Error while clearing the database: " + e.getMessage());
        }
    }

    public static String[][] getAllProductsMatrix2() {
        String sql = "SELECT * FROM products";

        List<String[]> productList = new ArrayList<>();
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                String productAmount = rs.getString("product_amount");

                String[] productRow = {String.valueOf(id), productName, productAmount};
                
                productList.add(productRow);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String[][] productMatrix = new String[productList.size()][3];
        for (int i = 0; i < productList.size(); i++) {
            productMatrix[i] = productList.get(i); 
        }

        return productMatrix;
    }

    public static void subtractProductAmount(String productName, int amountToSubtract) {
        String selectSql = "SELECT product_amount FROM products WHERE product_name = ?";
        String updateSql = "UPDATE products SET product_amount = ? WHERE product_name = ?";
        String deleteSql = "DELETE FROM products WHERE product_name = ?";

        try (Connection conn = connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setString(1, productName);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int currentAmount = rs.getInt("product_amount");
                int newAmount = currentAmount - amountToSubtract;

                if (newAmount < 0) {
                    System.out.println("Error: Cannot subtract more than the current amount.");
                    return;
                } else if (newAmount == 0) {
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.setString(1, productName);
                        deleteStmt.executeUpdate();
                        System.out.println("Product deleted from the second database: " + productName);
                    }
                } else {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, newAmount);
                        updateStmt.setString(2, productName);
                        updateStmt.executeUpdate();
                        System.out.println("Product amount updated in the second database: " + productName + " - new amount: " + newAmount);
                    }
                }
            } else {
                System.out.println("Error: Product not found in the second database.");
            }

        } catch (SQLException e) {
            System.out.println("Error while subtracting the product amount in the second database: " + e.getMessage());
        }
    }
}
