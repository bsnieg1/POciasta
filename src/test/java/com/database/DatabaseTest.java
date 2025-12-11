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

    @AfterEach
    public void tearDown() {
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

}