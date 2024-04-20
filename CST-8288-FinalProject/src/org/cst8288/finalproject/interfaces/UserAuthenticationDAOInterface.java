package org.cst8288.finalproject.interfaces;

/**
 * Interface for managing user authentication-related data access operations in the database.
 * This interface mandates implementations to provide essential authentication functionalities,
 * including methods to set a user's password and to verify a given password against the stored value.
 * 
 * Implementations of this interface are expected to handle direct interactions with the database 
 * to update and check password information, crucial for maintaining secure access to user accounts.
 *
 * @author Luis David Contreras
 * @version 1.0
 * @since 2024-04-20
 */
public interface UserAuthenticationDAOInterface {

    /**
     * Stores or updates a user's password in the database. This method is crucial for creating new users
     * or updating an existing user's password.
     *
     * @param userID The unique identifier for the user whose password is to be set.
     * @param password The password to store or update in the database.
     */
    void setPassword(int userID, String password);
    
    /**
     * Verifies if the provided password matches the one stored in the database for the user.
     * This method is used during the login process to ensure that the entered password is correct.
     *
     * @param userEmail The email address of the user trying to log in.
     * @param password The password provided during the login attempt.
     * @return true if the password matches the stored value, false otherwise.
     */
    boolean verifyPassword(String userEmail, String password);
}

