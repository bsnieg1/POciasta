package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

  
    
    private static final String URL = "jdbc:sqlite:C:\\Users\\barto\\OneDrive\\Pulpit\\test_czy_to_zadziala\\ciasta\\src\\main\\resources\\com\\MyDatabase.db"; // Change the path to your database file

    
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "product_name TEXT NOT NULL, "
                   + "product_amount TEXT NOT NULL)";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'products' created successfully (if not already created).");
        } catch (SQLException e) {
            System.out.println("Error while creating the table: " + e.getMessage());
        }
    }

  
    public static void insertProduct(String productName, String productAmount) {
        String sql = "INSERT INTO products(product_name, product_amount) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);   
            stmt.setString(2, productAmount); 
            stmt.executeUpdate();              
            System.out.println("Product added: " + productName + " - " + productAmount);
        } catch (SQLException e) {
            System.out.println("Error while inserting the product: " + e.getMessage());
        }
    }


    public static ResultSet getProducts() {
        String sql = "SELECT * FROM products";
        
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery(); 
        } catch (SQLException e) {
            System.out.println("Error while retrieving products: " + e.getMessage());
            return null;
        }
    }

    
    public static void deleteProduct(String productName) {
        String sql = "DELETE FROM products WHERE product_name = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);
            stmt.executeUpdate();            
            System.out.println("Product deleted: " + productName);
        } catch (SQLException e) {
            System.out.println("Error while deleting the product: " + e.getMessage());
        }
    }

  
    public static void clearDatabase() {
        String sql = "DELETE FROM products";  
    
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate(); 
            System.out.println("All data has been cleared from the database.");
        } catch (SQLException e) {
            System.out.println("Error while clearing the database: " + e.getMessage());
        }
    }

    
    public static String[][] getAllProductsMatrix() {
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
}
