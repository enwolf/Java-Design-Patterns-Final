package org.cst8288.finalproject.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link DataSource} class which provides singleton access to the database connection.
 * This test class verifies the singleton behavior of the DataSource class and the integrity of the database
 * connection it provides.
 * 
 * Tests include checking if multiple calls to getInstance() return the same object (singleton property),
 * and whether the connection obtained from DataSource is valid and not closed upon retrieval.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see DataSource
 */
public class DataSourceTest {

    /**
     * Tests the singleton property of the DataSource class.
     * Ensures that multiple calls to {@link DataSource#getInstance()} return the exact same object instance.
     */
    @Test
    public void testGetInstance() 
    {
        DataSource instance1 = DataSource.getInstance();
        DataSource instance2 = DataSource.getInstance();
        assertSame("Expected both instances to be the same", instance1, instance2);
    }

    /**
     * Tests the connection provided by DataSource to ensure it is not null and is open.
     * It checks that the connection is active by asserting it's not closed right after being obtained.
     * If the connection cannot be opened, or if it is closed, the test will fail.
     */
    @Test
    public void testGetConnectionToDatabase() 
    {
        DataSource instance = DataSource.getInstance();

        try 
        {
            Connection connection = instance.getConnectionToDatabase();
            assertNotNull("Connection should not be null", connection);
            assertFalse("Connection should be open", connection.isClosed());
            connection.close();
        }
        catch (SQLException e) 
        {
            fail("SQL Exception thrown: " + e.getMessage());
        }
    }
}

