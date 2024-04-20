package org.cst8288.finalproject.interfaces;

import org.cst8288.finalproject.users.UserTemp;

/**
 * Provides an interface for user type verification across different modules of the application.
 * Implementing this interface requires the definition of a method to verify the type of a user.
 * This is useful for enforcing user type restrictions and ensuring that operations are performed 
 * by users with appropriate permissions or roles.
 * 
 * The interface aims to enhance modularity and security by clearly separating user type verification
 * logic which can be implemented according to the specific requirements of different user models 
 * or business logic scenarios.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-12
 * @see UserTemp
 */
public interface UserTypeInterface {
    
    /**
     * Verifies if the user matches a specific user type necessary for certain operations or permissions.
     * This method should be implemented to define specific type-checking logic based on the application's needs.
     * 
     * @param user A {@link UserTemp} object representing the user whose type is to be verified.
     * @return true if the user meets the type criteria, false otherwise.
     */
    public boolean VerifiyUserType(UserTemp user);

}

