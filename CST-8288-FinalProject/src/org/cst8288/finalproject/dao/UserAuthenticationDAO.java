package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.interfaces.UserAuthenticationDAOInterface;

public class UserAuthenticationDAO implements UserAuthenticationDAOInterface {
    private Map<Integer, String> passwordMap; // Maps userID to password
    private Map<String, Integer> emailMap; // Maps email to userID

    public UserAuthenticationDAO() 
    {
        this.passwordMap = new HashMap<>();
        this.emailMap = new HashMap<>();
    }

    @Override
    public void setPassword(int userID, String password) 
    {
        passwordMap.put(userID, password);
    }
    
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

    /*
    @Override
    public boolean verifyPassword(int userID2, String password) {
        Integer userID = emailMap.get(userID2);
        if (userID != null) {
            String storedPassword = passwordMap.get(userID);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }
*/

}

