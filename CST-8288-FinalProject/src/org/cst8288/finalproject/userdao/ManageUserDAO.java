package org.cst8288.finalproject.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;

public class ManageUserDAO implements ManageUserDAOInterface {
    private static final String JDBC_URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

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
    public void addUser(User user) 
    {

        String sqlQuery = "INSERT INTO user (FirstName, LastName, Email, Password, UserType) VALUES (?, ?, ?, ?, ?)";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();  // Proper connection handling
             PreparedStatement statement = dbConnection.prepareStatement(sqlQuery)) 
        {
            statement.setString(1, user.getUserFirstName());
            statement.setString(2, user.getUserLastName());
            statement.setString(3, user.getEmailAddress());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUserType().toString());

            statement.executeUpdate();
        } 
        catch (SQLException e)
        {
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
    public User returnUser(int userID) 
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
    public void updateUser(int userID, User updatedUser) {
        String sql = "UPDATE users SET userFirstName = ?, userLastName = ?, emailAddress = ?, userType = ? WHERE userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedUser.getUserFirstName());
            statement.setString(2, updatedUser.getUserLastName());
            statement.setString(3, updatedUser.getEmailAddress());
            statement.setString(4, updatedUser.getUserType().toString());
            statement.setInt(5, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
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

    private AbstractUser extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        //int userID = resultSet.getInt("userID");
        String userFirstName = resultSet.getString("userFirstName");
        String userLastName = resultSet.getString("userLastName");
        String emailAddress = resultSet.getString("emailAddress");
        String password = resultSet.getString("emailAddress");
        UserType userType = UserType.valueOf(resultSet.getString("userType"));
        return new User(userFirstName, userLastName,  emailAddress,  password,  userType)  ;
    }
    
}
