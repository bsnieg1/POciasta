package com.database;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PantryTest {

    @BeforeEach
    public void setUp() {
        Pantry.createTable();
        Pantry.clearDatabase2();
    }

    @Test
    public void testConnect() {
        Connection conn = Pantry.connect();
        assertNotNull(conn, "Connection should not be null");
    }

    @Test
    public void testCreateTable() {
        assertDoesNotThrow(() -> Pantry.createTable(), "Creating table should not throw an exception");
    }

    @Test
    public void testInsertOrUpdateProduct() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        ResultSet rs = Pantry.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test Product", rs.getString("product_name"));
            assertEquals(100, rs.getInt("product_amount"));
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testDeleteProduct() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.deleteProduct("Test Product");
        ResultSet rs = Pantry.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after deletion");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testClearDatabase2() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.clearDatabase2();
        ResultSet rs = Pantry.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after clearing the database");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testGetAllProductsMatrix2() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        String[][] products = Pantry.getAllProductsMatrix2();
        assertEquals(1, products.length, "There should be one product in the matrix");
        assertEquals("Test Product", products[0][1]);
        assertEquals("100", products[0][2]);
    }

    @Test
    public void testUpdateProductAmount() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.insertOrUpdateProduct("Test Product", 50);
        ResultSet rs = Pantry.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test Product", rs.getString("product_name"));
            assertEquals(150, rs.getInt("product_amount"));
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testSubtractProductAmount() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.subtractProductAmount("Test Product", 50);
        ResultSet rs = Pantry.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test Product", rs.getString("product_name"));
            assertEquals(50, rs.getInt("product_amount"));
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testSubtractProductAmountToZero() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.subtractProductAmount("Test Product", 100);
        ResultSet rs = Pantry.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after subtracting to zero");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testSubtractProductAmountMoreThanAvailable() {
        Pantry.insertOrUpdateProduct("Test Product", 100);
        Pantry.subtractProductAmount("Test Product", 150);
        ResultSet rs = Pantry.getProducts();
        try {
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test Product", rs.getString("product_name"));
            assertEquals(100, rs.getInt("product_amount"), "Product amount should not change if subtracting more than available");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    @Test
    public void testInsertOrUpdateProductWithNegativeAmount() {
        Pantry.insertOrUpdateProduct("Test Product", -100);
        ResultSet rs = Pantry.getProducts();
        try {
            assertFalse(rs.next(), "ResultSet should be empty after attempting to insert a product with negative amount");
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }
}