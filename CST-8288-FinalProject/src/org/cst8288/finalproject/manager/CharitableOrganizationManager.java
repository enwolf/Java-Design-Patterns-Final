package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.UserTemp;

/**
 * Manages operations specific to users identified as Charitable Organizations.
 * This class ensures that the operations related to Charitable Organizations are executed based on the 
 * UserType. It also maintains a reference to the InventoryManager to perform inventory-related operations
 * which might be needed by the Charitable Organization.
 *
 * Implements the {@link UserTypeInterface} to enforce type-specific operations and validations.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see UserTypeInterface
 * @see InventoryManager
 * @see UserTemp
 */
public class CharitableOrganizationManager implements UserTypeInterface{
    
    private UserTemp userObject;          // Instance of UserTemp to hold user information.
    private InventoryManager inventoryManagerObj;  // Instance of InventoryManager to manage inventory operations.
    
    /**
     * Constructs a new CharitableOrganizationManager with specified user and inventory manager.
     * 
     * @param user The user object containing data about the user, expected to be of type Charitable Organization.
     * @param inventoryManager The inventory manager object to handle inventory operations for the user.
     */
    public CharitableOrganizationManager(UserTemp user, InventoryManager inventoryManager)
    {
        this.userObject = user;
        this.inventoryManagerObj = inventoryManager;
    }

    /**
     * Validates if the provided UserTemp object represents a user of type Charitable Organization.
     * This method checks the user type of the given UserTemp object against the CHARITABLE_ORGANIZATION enum.
     * 
     * @param user The UserTemp object to be verified.
     * @return true if the user is of type Charitable Organization, false otherwise.
     */
    @Override
    public boolean VerifiyUserType(UserTemp user) 
    {       
        return user.getType() == UserType.CHARITABLE_ORGANIZATION;
    }
}
