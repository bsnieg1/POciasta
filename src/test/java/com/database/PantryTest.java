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

    @AfterEach
    public void tearDown() {
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








}