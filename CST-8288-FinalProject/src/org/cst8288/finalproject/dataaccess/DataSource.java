package org.cst8288.finalproject.dataaccess;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.cst8288.finalproject.logger.LMSLogger;

/**
 * The DataSource class manages the database connection for the application as a singleton.
 * It ensures only one instance of the database connection is created and used throughout the application.
 * The class provides methods for establishing a connection to the database and handling configurations
 * read from a properties file.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-03-19
 * @see Connection
 * @see DriverManager
 * @see Properties
 */
public class DataSource {
	
    private static DataSource dbSingleInstance;
    private LMSLogger logger = LMSLogger.getInstance();
    private String className = getClass().getSimpleName();

    // Private constructor to prevent instantiation
    private DataSource() 
    {
    	
    }

    /**
     * Retrieves the singleton instance of DataSource. Creates a new instance if it doesn't exist.
     *
     * @return the single instance of DataSource
     */
    public static DataSource getInstance() 
    {
        if (dbSingleInstance == null) 
        {
            dbSingleInstance = new DataSource();
        }
        return dbSingleInstance;
    }

    /**
     * Establishes and returns a new connection to the database.
     * It reads the database connection information from a properties file and handles SQL and ClassNotFound exceptions.
     *
     * @return a new database Connection, or null if unable to establish
     * 
     * @see DriverManager#getConnection(String, String, String)
     * @see SQLException
     * @see ClassNotFoundException
     */
    public Connection getConnectionToDatabase() 
    {
        logger.debug(className + ": getConnectionToDatabase - Starting method");
        Connection connection = null; 
        
        String[] connectionInfo = openPropsFile();

        try 
        {
            Class.forName(connectionInfo[0]);
            connection = DriverManager.getConnection(connectionInfo[1], connectionInfo[2], connectionInfo[3]);
        }
        catch (SQLException e) 
        {    
            logger.logException(e);
        }
        catch (ClassNotFoundException e) 
        {
            logger.logException(e);
        }
        return connection; 
    }

    /**
     * Opens and reads the database properties file.
     * Logs IOExceptions that occur during the process.
     *
     * @return an array of Strings containing the driver, URL, userName, and password for the database connection
     * 
     * @see Files#newInputStream(java.nio.file.Path)
     * @see Properties#load(InputStream)
     * @see IOException
     */
    private static String[] openPropsFile() 
    {
        Properties props = new Properties();
        String className = DataSource.class.getSimpleName();
        LMSLogger logger = LMSLogger.getInstance();
        
        try (InputStream in = Files.newInputStream(Paths.get("data/database.properties"))) 
        {
            props.load(in);
        }
        catch(IOException e) 
        {
            logger.logException(e);
        }
        String driver = props.getProperty("dbDriver");
        String connectionString = props.getProperty("host");
        String userName = props.getProperty("username");
        String password = props.getProperty("password");

        String[] info = new String[4];
        info[0] = driver;
        info[1] = connectionString;
        info[2] = userName;
        info[3] = password;

        return info;
    }
}
