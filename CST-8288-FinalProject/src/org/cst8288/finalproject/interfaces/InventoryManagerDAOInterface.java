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
	
	void addInventoryItem(Item inventoryItem) throws SQLException;
	void updateInventoryItem(Item inventoryItem) throws SQLException;
	void removeInventoryitem(int itemID) throws SQLException;
	Item getSingleInventoryItemByID(int itemID) throws SQLException;
	public boolean isItemSurplus(int itemID) throws SQLException;
	List<Item> getALLInventoryItems() throws SQLException;
	List<Item> getALLSurplusInventoryItems() throws SQLException;

}
