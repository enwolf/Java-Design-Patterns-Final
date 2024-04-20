package org.cst8288.finalproject.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.cst8288.finalproject.dto.Item;

/**
 * Interface defining the operations for managing inventory items in the database.
 * This interface includes methods for adding, updating, and removing inventory items,
 * as well as retrieving single items or lists of items based on specific criteria like
 * surplus status. It serves as a contract for any DAO (Data Access Object) implementation
 * that interacts with the inventory in the database, ensuring consistency and standardization
 * across different implementations.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-05
 */
public interface InventoryManagerDAOInterface {
	
	/**
     * Adds a new inventory item to the database.
     * 
     * @param inventoryItem The inventory item to be added.
     * @throws SQLException If an SQL exception occurs while adding the item.
     */
    void addInventoryItem(Item inventoryItem) throws SQLException;
    
    /**
     * Updates an existing inventory item in the database.
     * 
     * @param inventoryItem The inventory item to be updated.
     * @throws SQLException If an SQL exception occurs while updating the item.
     */
    void updateInventoryItem(Item inventoryItem) throws SQLException;
    
    /**
     * Removes an inventory item from the database based on its ID.
     * 
     * @param itemID The ID of the inventory item to be removed.
     * @throws SQLException If an SQL exception occurs while removing the item.
     */
    void removeInventoryitem(int itemID) throws SQLException;
    
    /**
     * Retrieves a single inventory item from the database based on its ID.
     * 
     * @param itemID The ID of the inventory item to retrieve.
     * @return The retrieved inventory item.
     * @throws SQLException If an SQL exception occurs while retrieving the item.
     */
    Item getSingleInventoryItemByID(int itemID) throws SQLException;
    
    /**
     * Checks if an inventory item with the given ID is surplus.
     * 
     * @param itemID The ID of the inventory item to check.
     * @return true if the item is surplus, false otherwise.
     * @throws SQLException If an SQL exception occurs while checking the surplus status.
     */
    boolean isItemSurplus(int itemID) throws SQLException;
    
    /**
     * Retrieves all inventory items from the database.
     * 
     * @return A list containing all inventory items.
     * @throws SQLException If an SQL exception occurs while retrieving the items.
     */
    List<Item> getALLInventoryItems() throws SQLException;
    
    /**
     * Retrieves all surplus inventory items from the database.
     * 
     * @return A list containing all surplus inventory items.
     * @throws SQLException If an SQL exception occurs while retrieving the items.
     */
    List<Item> getALLSurplusInventoryItems() throws SQLException;
}
