package org.cst8288.finalproject.userdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.users.UserInterface;

public class ManageUserDAO implements ManageUserDAOInterface {
    private static final String JDBC_URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private Connection connection;

    public ManageUserDAO() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection error
        }
    }

    @Override
    public void addUser(UserInterface user) {
        String sql = "INSERT INTO users (userID, userFirstName, userLastName, emailAddress, userType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserID());
            statement.setString(2, user.getUserFirstName());
            statement.setString(3, user.getUserLastName());
            statement.setString(4, user.getEmailAddress());
            statement.setString(5, user.getUserType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    @Override
    public void removeUser(int userID) {
        String sql = "DELETE FROM users WHERE userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    @Override
    public UserInterface returnUser(int userID) {
        String sql = "SELECT * FROM users WHERE userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return null;
    }

    @Override
    public void updateUser(int userID, UserInterface updatedUser) {
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
    public List<UserInterface> returnAllUsers() {
        List<UserInterface> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UserInterface user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return users;
    }

    private UserInterface extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("userID");
        String userFirstName = resultSet.getString("userFirstName");
        String userLastName = resultSet.getString("userLastName");
        String emailAddress = resultSet.getString("emailAddress");
        UserInterface.UserType userType = UserInterface.UserType.valueOf(resultSet.getString("userType"));
        return new UserInterface(userID, userFirstName, userLastName, emailAddress, userType);
    }

    
}
