package ciasta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Pantry {

    // Path to the second SQLite database file
    private static final String URL = "jdbc:sqlite:C:\\Users\\Bartek\\Desktop\\POciasta-main\\java apka\\przepisy\\src\\main\\java\\ciasta\\Pantry.db";  // Change the path to your second database file

    // Connect to the SQLite database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to second SQLite database has been established.");
        } catch (SQLException e) {
            System.out.println("Connection to second database failed: " + e.getMessage());
        }
        return conn;
    }

    // Create table if not exists (same structure as in the first database)
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "product_name TEXT NOT NULL, "
                   + "product_amount INTEGER NOT NULL)";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);  // Execute the SQL statement to create the table
            System.out.println("Table 'products' created successfully in the second database.");
        } catch (SQLException e) {
            System.out.println("Error while creating the table in the second database: " + e.getMessage());
        }
    }

    // Insert or update a product into the second database
    public static void insertOrUpdateProduct(String productName, int productAmount) {
        String selectSql = "SELECT product_amount FROM products WHERE product_name = ?";
        String updateSql = "UPDATE products SET product_amount = product_amount + ? WHERE product_name = ?";
        String insertSql = "INSERT INTO products(product_name, product_amount) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            // Check if the product already exists
            selectStmt.setString(1, productName);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Product exists, update its amount
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, productAmount);
                    updateStmt.setString(2, productName);
                    updateStmt.executeUpdate();
                    System.out.println("Product updated in the second database: " + productName + " - new amount added: " + productAmount);
                }
            } else {
                // Product doesn't exist, insert new product
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

    // Retrieve all products from the second database
    public static ResultSet getProducts() {
        String sql = "SELECT * FROM products";
        
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();  // Return the ResultSet containing the products
        } catch (SQLException e) {
            System.out.println("Error while retrieving products from the second database: " + e.getMessage());
            return null;
        }
    }

    // Delete a product by its name from the second database
    public static void deleteProduct(String productName) {
        String sql = "DELETE FROM products WHERE product_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);  // Set the product name to be deleted
            stmt.executeUpdate();            // Execute the deletion
            System.out.println("Product deleted from the second database: " + productName);
        } catch (SQLException e) {
            System.out.println("Error while deleting the product from the second database: " + e.getMessage());
        }
    }

    public static void clearDatabase2() {
        String sql = "DELETE FROM products";  // Delete all rows from the 'products' table
    
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();  // Execute the DELETE command
            System.out.println("All data has been cleared from the database.");
        } catch (SQLException e) {
            System.out.println("Error while clearing the database: " + e.getMessage());
        }
    }


    public static String[][] getAllProductsMatrix2() {
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
