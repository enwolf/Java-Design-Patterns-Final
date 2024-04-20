package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.UserTemp;

/**
 * Manages retailer-specific operations by interfacing with the {@link InventoryManager} to handle inventory
 * related tasks. This manager ensures that operations specific to retailers are executed only if the user 
 * is verified as a retailer based on the user type.
 * 
 * Implements {@link UserTypeInterface} to enforce user type verification before performing any operations,
 * which helps in maintaining system integrity and preventing unauthorized access to retailer functionalities.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see UserTypeInterface
 * @see InventoryManager
 * @see UserTemp
 */
public class RetailerManager implements UserTypeInterface {

    private UserTemp userObject;           // User object for accessing user details.
    private InventoryManager inventoryManagerObj;  // Inventory manager for handling inventory operations.
    
    /**
     * Constructor to initialize a RetailerManager with a user object and an inventory manager.
     * 
     * @param user A {@link UserTemp} object representing the user.
     * @param inventoryManager An {@link InventoryManager} object for managing inventory.
     */
    public RetailerManager(UserTemp user, InventoryManager inventoryManager) 
    {
        this.userObject = user;
        this.inventoryManagerObj = inventoryManager;
    }

    /**
     * Verifies if the given user is of type 'RETAILER'. This method is crucial for ensuring that
     * retailer-specific operations are performed only by users who are verified as retailers.
     * 
     * @param user The {@link UserTemp} object to verify.
     * @return true if the user is a retailer, false otherwise.
     */
    @Override
    public boolean VerifiyUserType(UserTemp user) 
    {
        return user.getType() == UserType.RETAILER;
    }
}
