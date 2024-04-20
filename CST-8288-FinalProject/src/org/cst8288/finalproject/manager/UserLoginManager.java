package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.ManageUserDAO;

/**
 * Manages user login processes by coordinating with {@link ManageUserDAO} for user data access and 
 * {@link UserPasswordManager} for password verification. This class encapsulates the logic needed to authenticate 
 * users based on email and password and to handle user logout.
 * 
 * The loginUser method checks user credentials and allows access if they are valid, whereas the logoutUser method 
 * handles user deauthentication and optional cleanup actions such as removing user sessions or records if necessary.
 * 
 * @author Robin Phillis, Luis David Contreras
 * @version 2.0
 * @since 2024-04-20
 * @see ManageUserDAO
 * @see UserPasswordManager
 */
public class UserLoginManager {
    private ManageUserDAO manageUserDAO;             // DAO for accessing user data.
    private UserPasswordManager userPasswordManager; // Manager for password verification functionalities.

    /**
     * Constructs a UserLoginManager with specified user data access object and password manager.
     * 
     * @param manageUserDAO A {@link ManageUserDAO} object for accessing user data.
     * @param userPasswordManager A {@link UserPasswordManager} object for managing user password verification.
     */
    public UserLoginManager(ManageUserDAO manageUserDAO, UserPasswordManager userPasswordManager) {
        this.manageUserDAO = manageUserDAO;
        this.userPasswordManager = userPasswordManager;
    }

    /**
     * Attempts to log in a user using their email and password.
     * 
     * @param userEmail The email address of the user trying to log in.
     * @param password The password provided by the user for login.
     * @return true if the login is successful (i.e., credentials are valid), false otherwise.
     */
    public boolean loginUser(String userEmail, String password) {
        if (userPasswordManager.verifyPassword(userEmail, password)) {
            System.out.println("User logged in successfully.");
            return true;
        } else {
            System.out.println("User login failed. Invalid credentials.");
            return false;
        }
    }

    /**
     * Logs out a user from the application by potentially removing their user session or related data.
     * 
     * @param userID The unique identifier of the user to log out.
     */
    public void logoutUser(int userID) {
        manageUserDAO.removeUser(userID);
        System.out.println("User logged out successfully.");
    }

}
