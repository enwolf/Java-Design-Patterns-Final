package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.UserAuthenticationDAO;

/**
 * Manages user password operations by interfacing with the {@link UserAuthenticationDAO}.
 * This class provides functionality to set and verify user passwords, thus encapsulating
 * the password management logic and delegating data access responsibilities to the DAO layer.
 * It serves as a part of the service layer in the application architecture, ensuring
 * that password-related actions are executed securely and efficiently.
 * 
 * @author Robin Phillis, Luis David Contreras
 * @version 2.0
 * @since 2024-04-19
 * @see UserAuthenticationDAO
 */
public class UserPasswordManager {
    
    private UserAuthenticationDAO userAuthenticationDAO;

    /**
     * Constructs a new UserPasswordManager with a specific instance of {@link UserAuthenticationDAO}.
     * This constructor allows for dependency injection, facilitating easier testing and configuration.
     *
     * @param userAuthenticationDAO The UserAuthenticationDAO instance to be used for password operations.
     */
    public UserPasswordManager(UserAuthenticationDAO userAuthenticationDAO) 
    {
        this.userAuthenticationDAO = userAuthenticationDAO;
    }

    /**
     * Sets a new password for a user identified by their userID.
     * This method delegates the actual operation to the {@link UserAuthenticationDAO}, providing a high-level
     * abstraction for setting passwords.
     *
     * @param userID The unique identifier for the user whose password is to be set.
     * @param password The new password to be set for the user.
     */
    public void setPassword(int userID, String password) 
    {
        userAuthenticationDAO.setPassword(userID, password);
    }

    /**
     * Verifies if the provided password matches the stored password for a user identified by their email.
     * This method utilizes the {@link UserAuthenticationDAO} to perform the verification against the database.
     *
     * @param userEmail The email of the user whose password is to be verified.
     * @param password The password to verify.
     * @return true if the provided password matches the stored password, false otherwise.
     */
    public boolean verifyPassword(String userEmail, String password) 
    {
        return userAuthenticationDAO.verifyPassword(userEmail, password);
    }

}

