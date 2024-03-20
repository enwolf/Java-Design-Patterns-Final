package org.cst8288.finalproject.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.junit.jupiter.api.Test;

class DataSourceTest {

    @Test
    public void testGetInstance() 
    {
        DataSource instance1 = DataSource.getInstance();
        DataSource instance2 = DataSource.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetConnectionToDatabase() 
    {
        DataSource instance = DataSource.getInstance();

        try 
        {
            Connection connection = instance.getConnectionToDatabase();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
            connection.close();
        }
        catch (SQLException e) 
        {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
