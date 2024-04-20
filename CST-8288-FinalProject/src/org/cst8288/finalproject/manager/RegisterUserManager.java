package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.interfaces.ManageUserDAOInterface;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.validator.UserValidator;

/**
 * Handles the registration process for various types of users including consumers, retailers, 
 * and charitable organizations. This manager class leverages {@link ManageUserDAOInterface} for data
 * persistence and {@link UserValidator} for validation purposes to ensure that only valid data is 
 * persisted to the database.
 * 
 * This class provides methods to register different types of user entities, each of which ensures 
 * that the user data is valid and subsequently saved using the appropriate DAO methods.
 * 
 * @author Robin Phillis, Luis David Contreras
 * @version 2.0
 * @since 2024-04-20
 * @see ManageUserDAOInterface
 * @see UserValidator
 * @see AbstractUser
 * @see Consumer
 * @see Retailer
 * @see CharitableOrganization
 */
public class RegisterUserManager {
    
    private ManageUserDAOInterface manageUserDAO;  // DAO interface for user management operations.
    private UserValidator userValidator;           // Validator for checking user data validity.

    /**
     * Constructs a new RegisterUserManager with specified user DAO and validator.
     * 
     * @param manageUserDAO The user management DAO used for data persistence.
     * @param userValidator The validator used to ensure data integrity.
     */
    public RegisterUserManager(ManageUserDAOInterface manageUserDAO, UserValidator userValidator)
    {
        this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
    }

    /**
     * Registers a user in the database after validating the user data.
     * 
     * @param user The user to be registered.
     * @return The ID of the newly registered user or 0 if registration fails.
     */
    public int registerUser(AbstractUser user) 
    {
        if (userValidator.validateUser(user)) 
        {
            int ID = manageUserDAO.addUser(user);
            System.out.println("User registered successfully with ID: " + ID);
            return ID;
        }
        else 
        {
            System.out.println("User registration failed. Please check the input data.");
            return 0;
        }
    }

    /**
     * Registers a consumer in the database. This method does not perform validation.
     * 
     * @param consumer The consumer to be registered.
     */
    public void registerConsumer(Consumer consumer) 
    {
        manageUserDAO.addConsumerDetails(consumer); 
        System.out.println("Consumer registered successfully.");     
    }

    /**
     * Registers a retailer in the database. This method does not perform validation.
     * 
     * @param retailer The retailer to be registered.
     */
    public void registerRetailer(Retailer retailer) 
    {
        manageUserDAO.addRetailerDetails(retailer); 
        System.out.println("Retailer registered successfully.");        
    }

    /**
     * Registers a charitable organization in the database. This method does not perform validation.
     * 
     * @param organization The charitable organization to be registered.
     */
    public void registerCharitableOrg(CharitableOrganization organization) 
    {
        manageUserDAO.addCharitableOrganizationDetails(organization); 
        System.out.println("Charitable Organization registered successfully.");        
    }
}
