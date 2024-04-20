package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.ManageUserDAO;
import org.cst8288.finalproject.dao.UserAuthenticationDAO;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.validator.UserValidator;

/**
 * The UserManager class coordinates user management operations across the application.
 * It integrates functionalities provided by {@link ManageUserDAO}, {@link UserValidator}, and {@link UserAuthenticationDAO}
 * to perform user authentication, addition, deletion, and updates.
 *
 * It provides methods to authenticate users based on passwords, validate and add new users to the database,
 * update user details, delete users, and fetch users by ID or email. This central management of user operations
 * ensures that user data integrity and security are maintained throughout the application.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see ManageUserDAO
 * @see UserValidator
 * @see UserAuthenticationDAO
 * @see UserPasswordManager
 */
public class UserManager {
	
    private ManageUserDAO manageUserDAO;
    private UserValidator userValidator;
    private UserPasswordManager userPasswordManager; 
    private LMSLogger logger = LMSLogger.getInstance(); 
    
    /**
     * Constructs a UserManager with specified user DAO, validator, and authentication DAO.
     * 
     * @param manageUserDAO A {@link ManageUserDAO} object for user data management.
     * @param userValidator A {@link UserValidator} for validating user data.
     * @param userAuthDAO A {@link UserAuthenticationDAO} for user authentication operations.
     */
    public UserManager(ManageUserDAO manageUserDAO, UserValidator userValidator, UserAuthenticationDAO userAuthDAO) 
    {
        this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
        this.userPasswordManager = new UserPasswordManager(userAuthDAO); 
    }

    /**
     * Authenticates a user based on email and password.
     * 
     * @param email The email of the user to authenticate.
     * @param password The password for authentication.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateUserPassword(String email, String password) 
    {
    	AbstractUser user = getUserByEmail(email);
        if (user != null && userPasswordManager.verifyPassword(email, password)) 
        {
            logger.debug("Authentication successful for user: " + email);
            return true;
        }
        logger.warn("Authentication failed for user: " + email);
        return false;
    }
    
    /**
     * Validates and adds a user to the database.
     * 
     * @param user The {@link AbstractUser} to add.
     * @return true if the user is successfully added, false if validation fails.
     */
    public boolean validateAndAddUser(AbstractUser user) 
    {
        if (userValidator.validateUser(user)) 
        {
            try 
            {
                manageUserDAO.addUser(user);
                logger.info("User added successfully: " + user.getEmailAddress());
                return true;
            }
            catch (Exception e) 
            {
                logger.error("Error adding user: " + user.getEmailAddress());
                logger.logException(e);
            }
        } 
        else 
        {
            logger.warn("Validation failed for user: " + user.getEmailAddress());
        }
        return false;
    }
    
    /**
     * Validates and adds a user to the database.
     * 
     * @param user The {@link AbstractUser} to add.
     * @return true if the user is successfully added, false if validation fails.
     */
    public boolean updateUser(AbstractUser user) 
    {
        try 
        {
            manageUserDAO.updateUser(user);
            logger.info("User updated successfully: " + user.getEmailAddress());
            return true;
        }
        catch (Exception e) 
        {
            logger.error("Error updating user: " + user.getEmailAddress());
            logger.logException(e);
        }
        return false;
    }
    
    /**
     * Deletes a user from the database.
     * 
     * @param userId The ID of the user to delete.
     * @return true if the user is successfully deleted, false otherwise.
     */
    public boolean deleteUser(int userId) 
    {
        try 
        {
            manageUserDAO.removeUser(userId);
            logger.info("User deleted successfully: UserID = " + userId);
            return true;
        }
        catch (Exception e) 
        {
            logger.error("Error deleting user: UserID = " + userId);
            logger.logException(e);
        }
        return false;
    }

    /**
     * Retrieves a user by their user ID.
     * 
     * @param userId The ID of the user to retrieve.
     * @return The {@link AbstractUser} object if found, null otherwise.
     */
    public AbstractUser getUserById(int userId) 
    {
        try 
        {
            return manageUserDAO.returnUser(userId);
        }
        catch (Exception e) 
        {
            logger.error("Error retrieving user by ID: " + userId);
            logger.logException(e);
        }
        return null;
    }

    /**
     * Retrieves a user by their email address.
     * 
     * @param email The email of the user to retrieve.
     * @return The {@link AbstractUser} object if found, null otherwise.
     */
    public AbstractUser getUserByEmail(String email) 
    {
        try 
        {
            return manageUserDAO.returnUserByEmail(email);
        }
        catch (Exception e) 
        {
            logger.error("Error retrieving user by email: " + email);
            logger.logException(e);
        }
        return null;
    }
    
    /**
     * Populates additional details for the given user based on their user type.
     * 
     * @param user The user for which additional details need to be populated.
     * @return The user object with populated details, or null if the input user is null.
     */
    public AbstractUser populateUserDetails(AbstractUser user) 
    {        
    	if (user == null) return null;
        
        switch (user.getUserType()) 
        {
		    case RETAILER:
		        return manageUserDAO.getRetailerSpecificData(user.getUserId());
		    case CONSUMER:
		        return manageUserDAO.getConsumerSpecificData(user.getUserId());
		    case CHARITABLE_ORGANIZATION:
		        return manageUserDAO.getCharitableOrganizationSpecificData(user.getUserId());
		    default:
		        return manageUserDAO.returnUser(user.getUserId());
		}        
    }    
}
