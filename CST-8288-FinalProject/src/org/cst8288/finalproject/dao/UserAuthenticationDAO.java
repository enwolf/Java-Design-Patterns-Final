package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.interfaces.UserAuthenticationDAOInterface;


/**
 * Data Access Object (DAO) for managing user authentication within the database.
 * This class handles all operations related to user authentication, including verifying user passwords
 * and managing user credentials in a secure manner. It interacts with the database to check credentials
 * and update passwords, ensuring that all user authentication processes are robust and secure.
 *
 * The UserAuthenticationDAO class utilizes JDBC to perform SQL queries to validate user credentials
 * against stored data. It uses prepared statements to enhance security and performance by preventing SQL injection attacks
 * and optimizing database interaction. This class ensures that password verification and management are conducted
 * in a secure and efficient manner, adhering to best practices in database security.
 *
 * @author Luis David Contreras, Robin Phillis
 * @version 2.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.interfaces.UserAuthenticationDAOInterface
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 */
public class UserAuthenticationDAO implements UserAuthenticationDAOInterface {
    private Map<Integer, String> passwordMap; 
    private Map<String, Integer> emailMap; 

    /**
     * Constructs a new UserAuthenticationDAO object with empty password and email maps.
     */
    public UserAuthenticationDAO() 
    {
        this.passwordMap = new HashMap<>();
        this.emailMap = new HashMap<>();
    }

    /**
     * Sets the password for a user identified by their userID.
     *
     * @param userID   The ID of the user.
     * @param password The password to set.
     */
    @Override
    public void setPassword(int userID, String password) 
    {
        passwordMap.put(userID, password);
    }
    
    /**
     * Verifies whether the provided password matches the stored password for the given userEmail.
     *
     * @param userEmail The email of the user.
     * @param password  The password to verify.
     * @return True if the password is verified, false otherwise.
     */    
    @Override
    public boolean verifyPassword(String userEmail, String password) 
    {
        String sqlQuery = "SELECT Email, Password FROM user WHERE Email = ?";
        try (Connection connection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) 
        {
            
        	statement.setString(1, userEmail);
            
        	ResultSet resultSet = statement.executeQuery();
            
        	if (resultSet.next()) 
        	{
                String storedPassword = resultSet.getString("password");
                userEmail = resultSet.getString("Email");
                return storedPassword != null && storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

