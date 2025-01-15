package com.database;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @BeforeEach
    public void setUp() {
        Database.createTable();
        Database.clearDatabase();
    }

    @Test
    public void testConnect() {
        Connection conn = Database.connect();
        assertNotNull(conn, "Connection should not be null");
    }

    @Test
    public void testCreateTable() {
        assertDoesNotThrow(() -> Database.createTable(), "Creating table should not throw an exception");
    }

    @Test
    public void testInsertProduct() {
        Database.insertProduct("Test Product", "100");
        ResultSet rs = Database.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test Product", rs.getString("product_name"));
            assertEquals("100", rs.getString("product_amount"));
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testDeleteProduct() {
        Database.insertProduct("Test Product", "100");
        Database.deleteProduct("Test Product");
        ResultSet rs = Database.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after deletion");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testClearDatabase() {
        Database.insertProduct("Test Product", "100");
        Database.clearDatabase();
        ResultSet rs = Database.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after clearing the database");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testGetAllProductsMatrix() {
        Database.insertProduct("Test Product", "100");
        String[][] products = Database.getAllProductsMatrix();
        assertEquals(1, products.length, "There should be one product in the matrix");
        assertEquals("Test Product", products[0][1]);
        assertEquals("100", products[0][2]);
    }

    @Test
    public void testInsertMultipleProducts() {
        Database.insertProduct("Product 1", "50");
        Database.insertProduct("Product 2", "150");
        ResultSet rs = Database.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Product 1", rs.getString("product_name"));
            assertEquals("50", rs.getString("product_amount"));
            assertTrue(rs.next(), "ResultSet should have a second row");
            assertEquals("Product 2", rs.getString("product_name"));
            assertEquals("150", rs.getString("product_amount"));
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testDeleteNonExistentProduct() {
        assertDoesNotThrow(() -> Database.deleteProduct("Non Existent Product"), "Deleting a non-existent product should not throw an exception");
    }

    @Test
    public void testInsertProductWithEmptyName() {
        assertThrows(SQLException.class, () -> {
            Database.insertProduct("", "100");
        }, "Inserting a product with an empty name should throw an SQLException");
    }

    @Test
    public void testInsertProductWithNullName() {
        assertThrows(SQLException.class, () -> {
            Database.insertProduct(null, "100");
        }, "Inserting a product with a null name should throw an SQLException");
    }

    @Test
    public void testInsertProductWithEmptyAmount() {
        assertThrows(SQLException.class, () -> {
            Database.insertProduct("Test Product", "");
        }, "Inserting a product with an empty amount should throw an SQLException");
    }

    @Test
    public void testInsertProductWithNullAmount() {
        assertThrows(SQLException.class, () -> {
            Database.insertProduct("Test Product", null);
        }, "Inserting a product with a null amount should throw an SQLException");
    }

    @Test
    public void testGetProductsWhenEmpty() {
        ResultSet rs = Database.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty when there are no products");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testGetAllProductsMatrixWhenEmpty() {
        String[][] products = Database.getAllProductsMatrix();
        assertEquals(0, products.length, "Product matrix should be empty when there are no products");
    }
}