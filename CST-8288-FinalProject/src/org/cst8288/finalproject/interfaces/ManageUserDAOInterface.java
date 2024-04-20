package org.cst8288.finalproject.interfaces;

import java.util.List;

import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;

/**
 * Interface defining the data access operations required to manage user information in the database.
 * This interface is implemented by the ManageUserDAO class and includes methods for adding, updating, and 
 * deleting user records, as well as retrieving user details by different identifiers and user types.
 * It supports operations on different user subclasses such as Consumer, Retailer, and CharitableOrganization, 
 * thereby providing a flexible way to handle various user types dynamically at runtime.
 *
 * @author Robin Phillis, Luis David Contreras 
 * @version 2.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.users.AbstractUser
 * @see org.cst8288.finalproject.users.Consumer
 * @see org.cst8288.finalproject.users.Retailer
 * @see org.cst8288.finalproject.users.CharitableOrganization
 */
public interface ManageUserDAOInterface {
    
    /**
     * Adds a user record to the database and returns the auto-generated user ID.
     * @param user An instance of AbstractUser or its subclasses representing the user's details.
     * @return int The generated ID for the newly added user.
     */
    int addUser(AbstractUser user);
    
    /**
     * Removes a user record from the database based on user ID.
     * @param userID The ID of the user to be deleted.
     */
    void removeUser(int userID);
    
    /**
     * Updates existing user details in the database.
     * @param updatedUser An instance of AbstractUser containing updated details.
     */
    void updateUser(AbstractUser updatedUser);
    
    /**
     * Adds specific details for a consumer to the database.
     * @param consumer An instance of Consumer containing the consumer's details.
     */
    void addConsumerDetails(Consumer consumer);

    /**
     * Adds specific details for a retailer to the database.
     * @param retailer An instance of Retailer containing the retailer's details.
     */
    void addRetailerDetails(Retailer retailer);

    /**
     * Adds specific details for a charitable organization to the database.
     * @param organization An instance of CharitableOrganization containing the organization's details.
     */
    void addCharitableOrganizationDetails(CharitableOrganization organization);
    
    /**
     * Retrieves a user by their email address.
     * @param email The email address to search for.
     * @return AbstractUser A user instance if found, otherwise null.
     */
    AbstractUser returnUserByEmail(String email);
    
    /**
     * Retrieves a user by their user ID.
     * @param userID The user ID to search for.
     * @return AbstractUser A user instance if found, otherwise null.
     */
    AbstractUser returnUser(int userID);
    
    /**
     * Retrieves all users from the database.
     * @return List<AbstractUser> A list of all users.
     */
    List<AbstractUser> returnAllUsers();
    
    /**
     * Retrieves specific data for a retailer based on user ID.
     * @param userID The user ID of the retailer.
     * @return Retailer A retailer instance if found, otherwise null.
     */
    Retailer getRetailerSpecificData(int userID);
    
    /**
     * Retrieves specific data for a consumer based on user ID.
     * @param userID The user ID of the consumer.
     * @return Consumer A consumer instance if found, otherwise null.
     */
    Consumer getConsumerSpecificData(int userID);
    
    /**
     * Retrieves specific data for a charitable organization based on user ID.
     * @param userID The user ID of the charitable organization.
     * @return CharitableOrganization A charitable organization instance if found, otherwise null.
     */
    CharitableOrganization getCharitableOrganizationSpecificData(int userID);
    
}
