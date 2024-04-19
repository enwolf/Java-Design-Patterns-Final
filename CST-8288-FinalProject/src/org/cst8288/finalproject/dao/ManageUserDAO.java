package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.ManageUserDAOInterface;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;

public class ManageUserDAO implements ManageUserDAOInterface {
    private static final String JDBC_URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final LMSLogger LOGGER = LMSLogger.getInstance();

    private Connection connection;
/*
    public ManageUserDAO() 
    {
        try 
        {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (SQLException e) 
        {
            e.printStackTrace();        
        }
    }
*/
    @Override
    public void addUser(AbstractUser user) {
        String sqlQuery = "INSERT INTO user (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement statement = dbConnection.prepareStatement(sqlQuery)) {
            statement.setString(1, user.getUserFirstName());
            statement.setString(2, user.getUserLastName());
            statement.setString(3, user.getEmailAddress());
            statement.setString(4, ((User) user).getPassword());

            int rowsAffected = statement.executeUpdate();
            LMSLogger.getInstance().info("Added " + rowsAffected + " user(s) to the database from ManageUserDAO class.");
        } catch (SQLException e) {
            LMSLogger.getInstance().error("Error occurred while adding user to the database from ManageUserDAO class: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(AbstractUser updatedUser) {
        String sqlSelect = "SELECT userID FROM user WHERE Email = ?";
        String sqlUpdate = "UPDATE user SET FirstName = ?, LastName = ?, Email = ?, Password = ? WHERE userID = ?";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement selectStatement = dbConnection.prepareStatement(sqlSelect);
             PreparedStatement updateStatement = dbConnection.prepareStatement(sqlUpdate)) {
            selectStatement.setString(1, updatedUser.getEmailAddress());
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");

                updateStatement.setString(1, updatedUser.getUserFirstName());
                updateStatement.setString(2, updatedUser.getUserLastName());
                updateStatement.setString(3, updatedUser.getEmailAddress());
                updateStatement.setString(4, ((User) updatedUser).getPassword());
                updateStatement.setInt(5, userID);

                LOGGER.debug("Preparing to update database with: " + sqlUpdate);
                int rowsAffected = updateStatement.executeUpdate();
                
                LOGGER.info("Updated " + rowsAffected + " user(s). User details: " + updatedUser.toString());
                LMSLogger.getInstance().info("Updated " + rowsAffected + " user(s) in the database from ManageUserDAO class. Details: " + updatedUser.toString());
            }
        } catch (SQLException e) {
        	
            LMSLogger.getInstance().error("Error occurred while updating user in the database from ManageUserDAO class: " + e.getMessage());
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            
            e.printStackTrace();
        }
    }



    @Override
    public void removeUser(int userID) {
        
    	String sql = "DELETE FROM users WHERE userID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setInt(1, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    @Override
    public AbstractUser returnUser(int userID) 
    {
        String sql = "SELECT * FROM users WHERE userID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                if (resultSet.next()) 
                	return (User) extractUserFromResultSet(resultSet);                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return null;
    }
    
    @Override
    public AbstractUser returnUserByEmail(String email) {
        String sqlQuery = "SELECT * FROM user WHERE Email = ?";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement statement = dbConnection.prepareStatement(sqlQuery)) 
        {
            
            statement.setString(1, email);
            
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    return (User) extractUserFromResultSet(resultSet);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        return null;
    }




    @Override
    public List<AbstractUser> returnAllUsers() 
    {
        List<AbstractUser> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) 
        {
            while (resultSet.next()) 
            {
                User user = (User) extractUserFromResultSet(resultSet);
                users.add(user);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Helper method to extract user data from the ResultSet and create a User object.
     *
     * @param resultSet The ResultSet from which to extract user data.
     * @return A User object populated with data from the ResultSet.
     * @throws SQLException if accessing the ResultSet data fails.
     */
    private AbstractUser extractUserFromResultSet(ResultSet resultSet) throws SQLException 
    {
    	User user = new User();
        user.setUserId(resultSet.getInt("UserID"));
        user.setUserFirstName(resultSet.getString("FirstName"));
        user.setUserLastName(resultSet.getString("LastName"));
        user.setEmailAddress(resultSet.getString("Email"));
        user.setPassword(resultSet.getString("Password"));
        user.setUserType(UserType.valueOf(resultSet.getString("UserType").toUpperCase()));
        
        return user;
    }
    
}
