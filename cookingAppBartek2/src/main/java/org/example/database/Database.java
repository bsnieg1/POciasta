package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // Path to the SQLite database file
    private static final String URL = "jdbc:sqlite:/home/kali/IdeaProjects/cookingAppBartek2/src/main/resources/org/example/main/MyDatabase.db"; // Change the path to your database file

    // Connect to the SQLite database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            //System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Create table if not exists (for products with name and amount)
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "product_name TEXT NOT NULL, "
                   + "product_amount TEXT NOT NULL)";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // Execute the SQL statement to create the table if it doesn't exist
            System.out.println("Table 'products' created successfully (if not already created).");
        } catch (SQLException e) {
            System.out.println("Error while creating the table: " + e.getMessage());
        }
    }

    // Insert a product and its amount into the 'products' table
    public static void insertProduct(String productName, String productAmount) {
        String sql = "INSERT INTO products(product_name, product_amount) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);   // Set the product name
            stmt.setString(2, productAmount); // Set the product amount
            stmt.executeUpdate();              // Execute the insertion
            System.out.println("Product added: " + productName + " - " + productAmount);
        } catch (SQLException e) {
            System.out.println("Error while inserting the product: " + e.getMessage());
        }
    }

    // Retrieve all products and their amounts from the 'products' table
    public static ResultSet getProducts() {
        String sql = "SELECT * FROM products";
        
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery(); // Return the ResultSet containing the products
        } catch (SQLException e) {
            System.out.println("Error while retrieving products: " + e.getMessage());
            return null;
        }
    }

    // Delete a product by its name
    public static void deleteProduct(String productName) {
        String sql = "DELETE FROM products WHERE product_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName); // Set the product name to be deleted
            stmt.executeUpdate();            // Execute the deletion
            System.out.println("Product deleted: " + productName);
        } catch (SQLException e) {
            System.out.println("Error while deleting the product: " + e.getMessage());
        }
    }

    // Clear all data from the 'products' table
    public static void clearDatabase() {
        String sql = "DELETE FROM products";  // Delete all rows from the 'products' table
    
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();  // Execute the DELETE command
            System.out.println("All data has been cleared from the database.");
        } catch (SQLException e) {
            System.out.println("Error while clearing the database: " + e.getMessage());
        }
    }

    // Retrieve all products and their amounts as a 2D array (matrix)
    public static String[][] getAllProductsMatrix() {
        String sql = "SELECT * FROM products";
        
        // First, we need to count how many rows we will have to initialize the array size
        List<String[]> productList = new ArrayList<>();
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through the result set to access each row
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                String productAmount = rs.getString("product_amount");

                // Create a row for each product
                String[] productRow = {String.valueOf(id), productName, productAmount};
                
                // Add it to the product list
                productList.add(productRow);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Convert the List to a 2D array (matrix)
        String[][] productMatrix = new String[productList.size()][3];
        for (int i = 0; i < productList.size(); i++) {
            productMatrix[i] = productList.get(i); // Each row is an array of strings
        }

        // Return the matrix
        return productMatrix;
    }
}
