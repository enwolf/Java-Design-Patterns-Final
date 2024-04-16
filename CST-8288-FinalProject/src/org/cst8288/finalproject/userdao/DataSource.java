package org.cst8288.finalproject.userdao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.cst8288.finalproject.logger.LMSLogger;

public class DataSource {
    private static DataSource dbSingleInstance;
    private LMSLogger logger;
    private String className;

    private DataSource() {
        // Initialize logger and className
        logger = LMSLogger.getInstance();
        className = this.getClass().getName();
    }

    public static DataSource getInstance() {
        if (dbSingleInstance == null) {
            synchronized (DataSource.class) {
                if (dbSingleInstance == null) {
                    dbSingleInstance = new DataSource();
                }
            }
        }
        return dbSingleInstance;
    }

    public Connection getConnectionDatabase() throws SQLException {
        String[] props = openPropsFile();
        if (props != null && props.length == 3) {
            String dbUrl = props[0];
            String username = props[1];
            String password = props[2];
            return DriverManager.getConnection(dbUrl, username, password);
        } else {
            throw new SQLException("Error opening properties file.");
        }
    }

    private String[] openPropsFile() {
        Properties properties = new Properties();
        String[] props = new String[3]; 

        try (FileInputStream fis = new FileInputStream("database.properties")) {
            properties.load(fis);
            props[0] = properties.getProperty("dbUrl");
            props[1] = properties.getProperty("username");
            props[2] = properties.getProperty("password");
        } catch (IOException e) {
            logger.error(className + " Error reading properties file: " + e.getMessage());
        }

        return props;
    }

    
}

